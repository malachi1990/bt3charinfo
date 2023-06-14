package service.schedule

import model.schedule.Match
import model.schedule.Team


class MatchBuilder {
    fun buildAllPairings(allTeams: MutableList<Team>): MutableList<Match> {
        val homeGameProcessor = HomeGameProcessor()

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
                val match = if (homeGame) {
                    Match(homeTeam = team, week = 0, opposingTeam = opponent)
                } else {
                    Match(homeTeam = opponent, week = 0, opposingTeam = team)
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