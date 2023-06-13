package service.schedule

import model.schedule.Division
import model.schedule.Match
import model.schedule.Team
import java.util.stream.Collectors

import kotlin.random.Random


class HomeGameProcessor {
    private val rng: Random = Random.Default


    fun isHomeGame(team: Team, opponent: Team): Boolean {
        val team1HomeGames = team.getHomeGamesCount()
        val team2HomeGames = opponent.getHomeGamesCount()
        val team1CanHaveHomeGame = team1HomeGames < 8
        val team2CanHaveHomeGame = team2HomeGames < 8

        //handle divisional checks
        if (team.division === opponent.division) {
            return calculateDivisionalHomeTeam(team, opponent, team1CanHaveHomeGame, team2CanHaveHomeGame)
        }


        //check if a team has 8+ home games
        if (team1CanHaveHomeGame && !team2CanHaveHomeGame) {
            return true
        } else if (team2CanHaveHomeGame && !team1CanHaveHomeGame) {
            return false
        }

        //both have less than 8 home games
        if (team1CanHaveHomeGame && team2CanHaveHomeGame) {
            //check how many matches are left to be scheduled, and how many max possible home games can be scheduled
            val remainingGamesTeam1 = (15 - team.getSchedule().size).toLong()
            val remainingGamesTeam2 = (15 - opponent.getSchedule().size).toLong()
            val remainingPossibleHomeGamesTeam1 = 8 - team1HomeGames
            val remainingPossibleHomeGamesTeam2 = 8 - team2HomeGames

            //in the last stretch of the season, is this team in danger of not getting to 7/8 home games
            val team1NeedsPriority = remainingGamesTeam1 <= remainingPossibleHomeGamesTeam1
            val team2NeedsPriority = remainingGamesTeam2 <= remainingPossibleHomeGamesTeam2
            if (team1NeedsPriority && team2NeedsPriority) {
                println("Both " + team.name + " and " + opponent.name + " need priority")
                if (remainingPossibleHomeGamesTeam1 == remainingPossibleHomeGamesTeam2) {
                    println("teams need priority")
                    return rng.nextBoolean()
                }
                return remainingPossibleHomeGamesTeam1 > remainingPossibleHomeGamesTeam2 //prioritize the team who needs more home games
            } else if (team1NeedsPriority) {
                return true
            } else if (team2NeedsPriority) {
                return false
            }
            //check non-priority situations
            return if (team1HomeGames < team2HomeGames || team2HomeGames < team1HomeGames) {
                team1HomeGames < team2HomeGames // both under 8, but 1 is closer to their limit
            } else rng.nextBoolean()
            //both teams under 8 home games, so it doesn't really matter who gets the home game
        }
        return if (team1HomeGames == team2HomeGames) {
            rng.nextBoolean()
        } else team1HomeGames < team2HomeGames
        //if all else fails, prefer the team with the fewest home games
    }

    private fun calculateDivisionalHomeTeam(
        team: Team,
        opponent: Team,
        team1CanHaveHomeGame: Boolean,
        team2CanHaveHomeGame: Boolean
    ): Boolean {
        val team1DivisionalHomeGames = team.getDivisionalHomeGamesCount()
        val team2DivisionalHomeGames = opponent.getDivisionalHomeGamesCount()
        if (team1CanHaveHomeGame && team2CanHaveHomeGame) {
            if (team1DivisionalHomeGames == team2DivisionalHomeGames) {
                return rng.nextBoolean()
            } else if (team2DivisionalHomeGames == 2L) {
                return true
            } else if (team1DivisionalHomeGames == 2L) {
                return false
            }
            return team1DivisionalHomeGames < team2DivisionalHomeGames
        }
        //prioritize having 7 or 8 home games above having home map for divisional matches
        if (team1CanHaveHomeGame) {
            return true
        } else if (team2CanHaveHomeGame) {
            return false
        }
        return team1DivisionalHomeGames < team2DivisionalHomeGames
    }

    fun postProcessHomeGames(allTeams: List<Team>, mainSeasonMatches: MutableList<Match>) {
        postProcessDivisionals(allTeams, mainSeasonMatches)
        postProcessNormals(allTeams, mainSeasonMatches)
        postProcessHomeGamesByDivision(allTeams, mainSeasonMatches)
    }

    fun postProcessNormals(allTeams: List<Team>, mainSeasonMatches: MutableList<Match>) {
        val teamsWithTooFewHomeGames = allTeams.stream().filter { team: Team -> team.getHomeGamesCount() < 7 }.toList()
        val teamsWithTooManyHomeGames = allTeams.stream().filter { team: Team -> team.getHomeGamesCount() > 8 }.toList()
        if (teamsWithTooManyHomeGames.isEmpty() && teamsWithTooFewHomeGames.isEmpty()) {
            return  // in this case, no post-processing at this step because everyone is already at 7 or 8 home games.
        }

        //step 1; start by checking the matches between the teams in the 2 lists
        //ie if Androids have 9 home games and Cold has 6, and androids vs cold is home for Androids, then making it home for Cold is better
        for (team in teamsWithTooManyHomeGames) {
            for (opponent in teamsWithTooFewHomeGames) {
                if (team.getHomeGamesCount() == 7L || team.getHomeGamesCount() == 8L) {
                    break // check before making any more changes because these teams are in flux;
                }
                if (opponent.getHomeGamesCount() == 7L || opponent.getHomeGamesCount() == 8L) {
                    break // check before making any more changes because these teams are in flux;
                }
                val currentMatch: Match = team.getSchedule()!!
                    .stream().filter { match: Match ->
                        match.homeTeam
                            .equals(opponent) ||
                                match.getAwayTeam()!!.equals(opponent)
                    }.findFirst().orElseThrow {
                        IllegalStateException(
                            "Can't find valid match"
                        )
                    }
                if (currentMatch.homeTeam == team) {
                    updateMatches(currentMatch, team, opponent, mainSeasonMatches)
                }
            }
        }
    }

    fun postProcessDivisionals(allTeams: List<Team>, mainSeasonMatches: MutableList<Match>) {
        val teams =
            allTeams.stream().filter { team: Team -> team.getHomeGamesCount() < 7 || team.getHomeGamesCount() >= 8 }
                .sorted(Comparator.comparingLong { obj: Team -> obj.getHomeGamesCount() }).toList()
        for (team in teams) {
            //first make sure no one has < 7 home games
            if (team.getHomeGamesCount() < 7) {
                val awayGames: List<Match> = team.getSchedule()!!
                    .stream().filter { match: Match ->
                        !match.homeTeam
                            .equals(team)
                    }.toList() //don't remove
                for (game in awayGames) {
                    if (game.isDivisionalMatch() || game.getAwayTeam().getHomeGamesCount() >= 8) {
                        continue  //don't mess with divisional matches or teams with > 8 home games
                    }
                    val oldHomeTeam: Team = game.homeTeam
                    updateMatches(game, oldHomeTeam, team, mainSeasonMatches)
                    if (team.getHomeGamesCount() >= 7) {
                        break //once we get them to the minimum, don't look at any further matches;
                    }
                }
            } else if (team.getHomeGamesCount() > 8) {
                val homeGames: List<Match> = team.getSchedule()!!
                    .stream().filter { match: Match ->
                        match.homeTeam == team
                    }.toList() //don't remove
                for (game in homeGames) {
                    if (game.isDivisionalMatch() || game.getAwayTeam().getHomeGamesCount() < 7) {
                        continue  //don't mess with divisional matches or teams with < 8 home games
                    }
                    val oldHomeTeam: Team = game.homeTeam
                    updateMatches(game, oldHomeTeam, team, mainSeasonMatches)
                    if (team.getHomeGamesCount() <= 8) {
                        break //once we get them to the max, don't look at any further matches;
                    }
                }
            }
        }
    }


    fun postProcessHomeGamesByDivision(allTeams: List<Team>, mainSeasonMatches: MutableList<Match>) {
        val divisions = java.util.List.of(*Division.values())
        var divisionsNeedTuning = true
        println("starting home game post-processing by division")
        while (divisionsNeedTuning) {
            for (kai in divisions) {
                var count8Home = countTeamsWithXHomeGames(allTeams, 8, kai)
                var count7Home = countTeamsWithXHomeGames(allTeams, 7, kai)
                if (count8Home == count7Home) {
                    //ignore a division at 2 & 2
                    continue
                } else {
                    println("$kai has $count7Home with 7 home games and $count8Home teams with 8 home games")
                }
                val teamsWith8Games = allTeams.stream()
                    .filter { team: Team -> team.division === kai && team.getHomeGamesCount() >= 8 }
                    .toList()
                for (teamWith8HomeGames in teamsWith8Games) {
                    count8Home = countTeamsWithXHomeGames(allTeams, 8, kai)
                    count7Home = countTeamsWithXHomeGames(allTeams, 7, kai)
                    if (count8Home == count7Home) {
                        //ignore a division at 2 & 2
                        break
                    }
                    for (kai2 in divisions) {
                        if (teamWith8HomeGames.getHomeGamesCount() < 8) {
                            break
                        }
                        val eligibleTeams = allTeams.stream()
                            .filter { team: Team -> team.division === kai2 && team.getHomeGamesCount() < 8 }
                            .toList()
                        count8Home = countTeamsWithXHomeGames(allTeams, 8, kai2)
                        count7Home = countTeamsWithXHomeGames(allTeams, 7, kai2)
                        if (count8Home == count7Home) {
                            println("Excluding: $kai2 because it's already been equalized")
                            continue  //exclude divisions that are at 2 & 2
                        }
                        if (count8Home > 2 && kai !== kai2) {
                            continue  //can't move another home game into this division; only if necessary will this method change divisional matches
                        }
                        process7Count(mainSeasonMatches, teamWith8HomeGames, eligibleTeams)
                    }
                }
                val teamsWith7Games = allTeams.stream()
                    .filter { team: Team -> team.division === kai && team.getHomeGamesCount() < 8 }
                    .toList()
                for (teamwith7Games in teamsWith7Games) {
                    count8Home = countTeamsWithXHomeGames(allTeams, 8, kai)
                    count7Home = countTeamsWithXHomeGames(allTeams, 7, kai)
                    if (count8Home == count7Home) {
                        //ignore a division at 2 & 2
                        break
                    }
                    for (kai2 in divisions) {
                        if (teamwith7Games.getHomeGamesCount() > 7) {
                            break
                        }
                        val teamsWithMoreHomeGames = allTeams.stream()
                            .filter { team: Team -> team.division === kai2 && team.getHomeGamesCount() > 7 }
                            .collect(Collectors.toList())
                        count8Home = countTeamsWithXHomeGames(allTeams, 8, kai2)
                        count7Home = countTeamsWithXHomeGames(allTeams, 7, kai2)
                        if (count8Home == count7Home) {
                            println("Excluding: $kai2 because it's already been equalized")
                            continue  //exclude divisions that are at 2 & 2
                        }
                        if (count8Home > 2 && kai !== kai2) {
                            continue  //can't move another home game into this division; only if necessary will this method change divisional matches
                        }
                        process8Count(mainSeasonMatches, teamwith7Games, teamsWithMoreHomeGames)
                    }
                }
            }
            var finishedDivisions = 0
            for (kai in divisions) {
                val count8Home = countTeamsWithXHomeGames(allTeams, 8, kai)
                val count7Home = countTeamsWithXHomeGames(allTeams, 7, kai)
                if (count8Home == count7Home) {
                    finishedDivisions++
                }
            }
            if (finishedDivisions == 4) {
                println("ALL DIVISIONS EQUAL")
                divisionsNeedTuning = false
            }
        }
//        }
    }

    private fun process8Count(mainSeasonMatches: MutableList<Match>, teamwith7Games: Team, eligibleTeams: List<Team>) {
        for (teamWith8HomeGames in eligibleTeams) {
            if (teamWith8HomeGames.getHomeGamesCount() > 7) {
                continue  //shouldn't happen, but just in case . . .
            }
            val matchIsHomeGame = teamWith8HomeGames.getSchedule()
                .stream().anyMatch { match: Match ->
                    match.getAwayTeam() == teamWith8HomeGames
                }
            if (matchIsHomeGame) {
                val oldMatch: Match = teamWith8HomeGames.getSchedule()
                    .stream().filter { match: Match ->
                        match.getAwayTeam() == teamWith8HomeGames
                    }.findFirst().get()
                println("Flipping : " + oldMatch.getMatchDescription())
                updateMatches(oldMatch, teamwith7Games, teamWith8HomeGames, mainSeasonMatches)
                if (teamwith7Games.getHomeGamesCount() > 7) {
                    break
                }
            }
        }
    }

    private fun process7Count(
        mainSeasonMatches: MutableList<Match>,
        teamWith8HomeGames: Team,
        eligibleTeams: List<Team>
    ) {
        for (teamWith7HomeGames in eligibleTeams) {
            if (teamWith7HomeGames.getHomeGamesCount() > 7) {
                continue  //shouldn't happen, but just in case . . .
            }
            val matchIsHomeGame = teamWith8HomeGames.getSchedule()
                .stream().anyMatch { match: Match ->
                    match.getAwayTeam() == teamWith7HomeGames
                }
            if (matchIsHomeGame) {
                val oldMatch: Match = teamWith8HomeGames.getSchedule()
                    .stream().filter { match: Match ->
                        match.getAwayTeam() == teamWith7HomeGames
                    }.findFirst().get()
                println("Flipping : " + oldMatch.getMatchDescription())
                updateMatches(oldMatch, teamWith7HomeGames, teamWith8HomeGames, mainSeasonMatches)
                if (teamWith8HomeGames.getHomeGamesCount() < 8) {
                    break
                }
            }
        }
    }

    fun countTeamsWithXHomeGames(teams: List<Team>, HomeGameCount: Int, kai: Division): Long {
        return teams.stream()
            .filter { team: Team -> team.division === kai && team.getHomeGamesCount() == HomeGameCount.toLong() }
            .count()
    }

    fun updateMatches(oldMatch: Match, homeTeam: Team, awayTeam: Team, mainSeasonMatches: MutableList<Match>) {
        val replacementMatch: Match = oldMatch.flipHomeAndAway()
        homeTeam.getSchedule().remove(oldMatch)
        awayTeam.getSchedule().remove(oldMatch)
        homeTeam.addMatchToSchedule(replacementMatch)
        awayTeam.addMatchToSchedule(replacementMatch)
        mainSeasonMatches.remove(oldMatch)
        mainSeasonMatches.add(replacementMatch)
    }


}