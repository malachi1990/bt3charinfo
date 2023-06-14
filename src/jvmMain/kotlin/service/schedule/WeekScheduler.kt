package service.schedule

import model.schedule.Division
import model.schedule.Match
import model.schedule.Team
import java.util.function.Predicate
import java.util.stream.Collectors
import kotlin.random.Random

class WeekScheduler {

    private val rng: Random = Random.Default


    fun buildSchedule(mainSeasonMatches: MutableList<Match>): Map<Int, MutableList<Match>> {
        val weeks = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
        //need to make a copy of the list so that we can call this method multiple times
        val mainSeasonMatchesIdem: MutableList<Match> = ArrayList(mainSeasonMatches)
        val mainSeasonSchedule: MutableMap<Int, MutableList<Match>> = HashMap<Int, MutableList<Match>>()
        val divisionalPredicate: (Match) -> Boolean = Match::isDivisionalMatch

        //to make things a little easier, pair the divisionals first, as these have the most stringent constraints
        for (week in weeks) {
            val matches: MutableList<Match> = ArrayList<Match>()
            mainSeasonSchedule[week] = matches //create the placeholder list here so it doesn't have to be created later
            for (kai in Division.values()) {
                if (!kai.isDivisionalWeek(week)) {
                    continue
                }
                //first divisional week, each divisional will have 6 possible matches, then 4 (for the 2nd) then 2 in the last one)
                val eligibleMatches: MutableList<Match> =
                    mainSeasonMatchesIdem.stream().filter { match: Match ->
                        divisionalPredicate.invoke(match) &&
                                match.homeTeam.division === kai
                    }.collect(Collectors.toList())
                if (eligibleMatches.size == 2) {
                    for (div in eligibleMatches) {
                        div.week = week
                        matches.add(div)
                        mainSeasonMatchesIdem.remove(div)
                    }
                    continue
                }
                val pairedTeams: MutableList<Team> = ArrayList()
                val firstDiv: Match = eligibleMatches[rng.nextInt(eligibleMatches.size)]
                firstDiv.week = week
                matches.add(firstDiv)
                pairedTeams.add(firstDiv.homeTeam)
                pairedTeams.add(firstDiv.getAwayTeam())
                eligibleMatches.remove(firstDiv)
                eligibleMatches.removeIf { match: Match ->
                    pairedTeams.contains(match.getAwayTeam()) || pairedTeams.contains(
                        match.homeTeam
                    )
                }
                assert(
                    eligibleMatches.size == 1 //sanity check;
                )
                val otherDiv: Match = eligibleMatches[0]
                otherDiv.week = week
                matches.add(otherDiv)
                mainSeasonMatchesIdem.remove(firstDiv)
                mainSeasonMatchesIdem.remove(otherDiv)
            }
        }
        for ((week, weeklyMatches) in mainSeasonSchedule) {
            //                System.out.println("Pairing matches for week: " + week);
            val pairedTeams: MutableList<Team> = ArrayList()
            if (weeklyMatches.size > 0) {
                for (match in weeklyMatches) {
                    pairedTeams.add(match.homeTeam)
                    pairedTeams.add(match.getAwayTeam())
                }
            }
            var clearCount = 0
            val teamsAlreadyPaired: Predicate<Match> = Predicate<Match> { match ->
                !(pairedTeams.contains(match.homeTeam) || pairedTeams.contains(
                    match.getAwayTeam()
                ))
            }
            while (weeklyMatches.size < 8) {
                val eligibleMatches: List<Match> = mainSeasonMatchesIdem.stream().filter(teamsAlreadyPaired).toList()
                if (eligibleMatches.isEmpty() && weeklyMatches.size < 8) {
                    if (clearCount < 200) { //if a given iteration is too hard, just restart from scratch
                        clearCount++
                        for (match in weeklyMatches) {
                            if (!match.isDivisionalMatch()) {
                                match.week = 0
                                mainSeasonMatchesIdem.add(match)
                            }
                        }
                        weeklyMatches.removeIf { match: Match -> !match.isDivisionalMatch() }
                        //                            System.out.println("unable to completely assign week, restarting week");
                        pairedTeams.clear()
                        for (match in weeklyMatches) {
                            pairedTeams.add(match.homeTeam)
                            pairedTeams.add(match.getAwayTeam())
                        }
                        continue
                    }
                    println("Giving up on assigning week, reseeding")
                    return mainSeasonSchedule //return because once it fails to assign a match, the safeguards above will catch it.
                }
                val match: Match = eligibleMatches[rng.nextInt(eligibleMatches.size)]

                //cleanup after knowing the match should be assigned.
                match.week = week
                weeklyMatches.add(match)
                mainSeasonMatchesIdem.remove(match)
                pairedTeams.add(match.homeTeam)
                pairedTeams.add(match.getAwayTeam())
            }
        }
        return mainSeasonSchedule
    }

}