package service.schedule

import model.schedule.Match
import model.schedule.Team


class MatchBuilder {
    fun buildAllPairings(allTeams: List<Team>): List<Match>? {
        val homeGameProcessor = HomeGameProcessor();

        val mainSeasonMatches: MutableList<Match> = ArrayList<Match>()
        for (team in allTeams) {
            val remTeams = ArrayList(allTeams).stream().filter { remTeam: Team ->
                !remTeam.hasFoughtTeam(
                    team.name
                )
            }.toList()
            for (opponent in remTeams) {
                if (team.name.equals(opponent.name, ignoreCase = true)) {
                    continue
                }
                val homeGame: Boolean = homeGameProcessor.isHomeGame(team, opponent)
                var match: Match
                if (homeGame) {
                    match = Match(homeTeam = team, week = 0, opposingTeam = opponent)
                } else {
                    match = Match(homeTeam = opponent, week = 0, opposingTeam = team)
                }
                team.addMatchToSchedule(match)
                opponent.addMatchToSchedule(match)
                mainSeasonMatches.add(match)
            }
        }
        homeGameProcessor.postProcessHomeGames(allTeams, mainSeasonMatches)
        return mainSeasonMatches
    }
}