package model.schedule

import java.util.*
import java.util.function.Consumer
import java.util.stream.Collectors


class Team( var name: String,
            var division: Division,
            var matches: MutableList<Match>) {


    @Override
    fun getSchedule(): MutableList<Match> {
        matches.sortWith(Comparator.comparingInt { obj: Match -> obj.week })
        return matches
    }

    fun setSchedule(schedule: MutableList<Match>) {
        this.matches = schedule
    }

    fun hasFoughtTeam(opposingTeamName: String?): Boolean {
        return getSchedule().stream().anyMatch { match: Match ->
            match.getAwayTeam()
                .name == opposingTeamName || match.homeTeam.name == opposingTeamName
        }
    }

    fun hasBeenPairedForWeek(week: Int): Boolean {
        return getSchedule()!!.stream().anyMatch { match: Match -> match.week == week }
    }


    fun getHomeGamesCount(): Long {
        return getSchedule().stream().filter { match: Match ->
            match.homeTeam
                .name == this.name
        }.count()
    }

    fun getHomeGames(): List<Match>? {
        return getSchedule().stream().filter { match: Match ->
            match.homeTeam
                .name == this.name
        }.collect(Collectors.toList())
    }

    fun getDivisionalHomeGamesCount(): Long {
        return getSchedule().stream().filter { match: Match ->
            match.homeTeam
                .name == this.name && match.isDivisionalMatch()
        }.count()
    }

    fun addMatchToSchedule(match: Match) {
        matches.add(match)
    }

    fun clearSchedule() {
        matches.clear()
    }

    fun printSchedule(): String {
        val builder = StringBuilder()
        builder.append("Schedule for: ").append(name).append('\n')
        //use the getter so it's sorted
        getSchedule()!!.forEach(Consumer { match: Match ->
            builder.append(
                match.getFullDescription()
            ).append('\n')
        })
        builder.append("Home matches: ").append(getHomeGamesCount()).append('\n')
        return builder.toString()
    }


    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val team = o as Team
        return name == team.name && division === team.division
    }

    override fun hashCode(): Int {
        return Objects.hash(name, division)
    }
}