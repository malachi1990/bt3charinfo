package service.schedule

import model.schedule.Division
import model.schedule.Match
import model.schedule.Team
import service.schedule.writer.MarkdownWriter
import service.schedule.writer.TextWriter
import java.util.*


class ScheduleGenerator {


    fun generateSchedule(northKai: List<Team>, eastKai: List<Team>, westKai: List<Team>, southKai: List<Team>) {
        val allTeams: MutableList<Team> = ArrayList()
        allTeams.addAll(northKai)
        allTeams.addAll(eastKai)
        allTeams.addAll(westKai)
        allTeams.addAll(southKai)
        println("Generating matches")
        val seasonSchedule: Map<Int, List<Match>> = buildSchedule(allTeams)
        val textWriter = TextWriter()
        textWriter.writeToFile(seasonSchedule, allTeams, "./schedule.txt")
        val markdownWriter = MarkdownWriter()
        markdownWriter.printScheduleByTeam(allTeams, "./scheduleMarkdown.md")
        markdownWriter.printScheduleByWeek(seasonSchedule, "./scheduleByWeek.md")
    }

    fun buildSchedule(allTeams: MutableList<Team>): Map<Int, MutableList<Match>> {
        val mainSeasonMatches: MutableList<Match> = MatchBuilder().buildAllPairings(allTeams)
        var seasonSchedule: Map<Int, MutableList<Match>>
        val weekScheduler = WeekScheduler()
        //basically - keep trying till it works. locally, it took < ~10 seconds to generate a full schedule.
        //the retry is mostly caused by the rng trying to work with the scheduling constraints.
        do {
            seasonSchedule = weekScheduler.buildSchedule(mainSeasonMatches)
        } while (seasonSchedule.entries.stream()
                .anyMatch { (_, value): Map.Entry<Int?, List<Match?>> -> value.size < 8 }
        )
        allTeams.sortedWith(Comparator.comparing { team: Team -> team.division.ordinal })
        println("Schedule by team")
        //        allTeams.forEach(team -> System.out.println(team.printSchedule()));
        println(
            "North Kai teams with 8 home games: " + allTeams.stream()
                .filter { team: Team -> team.division === Division.NORTH_KAI && team.getHomeGamesCount() == 8L }
                .count())
        println(
            "North Kai teams with 7 home games: " + allTeams.stream()
                .filter { team: Team -> team.division === Division.NORTH_KAI && team.getHomeGamesCount() == 7L }
                .count())
        println()
        println(
            "East Kai teams with 8 home games: " + allTeams.stream()
                .filter { team: Team -> team.division === Division.EAST_KAI && team.getHomeGamesCount() == 8L }
                .count())
        println(
            "East Kai teams with 7 home games: " + allTeams.stream()
                .filter { team: Team -> team.division === Division.EAST_KAI && team.getHomeGamesCount() == 7L }
                .count())
        println()
        println(
            "West Kai teams with 8 home games: " + allTeams.stream()
                .filter { team: Team -> team.division === Division.WEST_KAI && team.getHomeGamesCount() == 8L }
                .count())
        println(
            "West Kai teams with 7 home games: " + allTeams.stream()
                .filter { team: Team -> team.division === Division.WEST_KAI && team.getHomeGamesCount() == 7L }
                .count())
        println()
        println(
            "South Kai teams with 8 home games: " + allTeams.stream()
                .filter { team: Team -> team.division === Division.SOUTH_KAI && team.getHomeGamesCount() == 8L }
                .count())
        println(
            "South Kai teams with 7 home games: " + allTeams.stream()
                .filter { team: Team -> team.division === Division.SOUTH_KAI && team.getHomeGamesCount() == 7L }
                .count())
        seasonSchedule.forEach { (_: Int, matches: MutableList<Match>) ->
            matches.shuffle()
        }
        return seasonSchedule
    }
}
//to adjust for new seasons, the only thing that *should* need to be done is rearrange the teams into the right Kais
private val northKaiTeams = listOf("Buu Saga", "Earth Defenders", "Muscle", "Resurrected Warriors")
private val eastKaiTeams = listOf("Namek", "Royals", "GT", "Cold")
private val westKaiTeams = listOf("Rugrats", "Hybrids", "Budokai", "Derp")
private val southKaiTeams = listOf("Androids", "Kaiju", "Sentai", "Cinema")

fun main() {
    val self = ScheduleGenerator()
    self.generateSchedule(
        northKaiTeams.stream().map { team: String ->Team(team, Division.NORTH_KAI, mutableListOf())}.toList(),
        eastKaiTeams.stream().map { team: String -> Team( team, Division.EAST_KAI, mutableListOf()) }.toList(),
        westKaiTeams.stream().map { team: String ->Team(team, Division.WEST_KAI, mutableListOf() )}.toList(),
        southKaiTeams.stream().map { team: String -> Team(team, Division.SOUTH_KAI, mutableListOf() ) }.toList()
    )
}