package service.schedule.writer

import model.schedule.Match
import model.schedule.Team
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.util.function.Consumer


class MarkdownWriter {
    fun printScheduleByWeek(seasonSchedule: Map<Int?, List<Match>>, filePath: String) {
        val schedule = buildScheduleByWeek(seasonSchedule)
        try {
            Files.writeString(Paths.get(filePath), schedule, StandardOpenOption.APPEND, StandardOpenOption.CREATE)
        } catch (e: Exception) {
            println("unable to write to file: " + e.localizedMessage)
        }
    }

    fun buildScheduleByWeek(seasonSchedule: Map<Int?, List<Match>>): String {
        println("Writing schedule by week to markdown file.")
        val builder = StringBuilder()
        seasonSchedule.forEach { (week: Int?, matches: List<Match?>) ->
            builder.append("### Week ").append(week).append('\n')
            builder.append(getScheduleWeekHeader()).append('\n')
            matches.forEach{ match ->
                builder.append(
                    getScheduleMarkdown(
                        match,
                        matches.indexOf(match) + 1
                    )
                )
            }
            builder.append('\n')
        }
        return builder.toString()
    }


    fun printScheduleByTeam(allTeams: List<Team>, filePath: String) {
        val schedule = buildScheduleByTeam(allTeams)
        try {
            Files.writeString(Paths.get(filePath), schedule, StandardOpenOption.APPEND, StandardOpenOption.CREATE)
        } catch (e: Exception) {
            println("unable to write to file: " + e.localizedMessage)
        }
    }

    fun buildScheduleByTeam(allTeams: List<Team>): String {
        println("Printing team schedule")
        val builder = StringBuilder()
        allTeams.forEach(Consumer { team: Team ->
            builder.append("### ").append(team.name).append("\n\n")
            builder.append(getTeamScheduleHeader()).append('\n')
            builder.append(getScheduleMarkdown(team))
            builder.append('\n')
        })
        return builder.toString()
    }

    private fun getTeamScheduleHeader(): String? {
        return """
                |Match          |  Home Team            | Away Team        |
                | :-------------| :---------------------| :----------------|
                """.trimIndent()
    }

    private fun getScheduleWeekHeader(): String? {
        return """
                |Match          |  Home Team            | Away Team        | Winner           |
                | :-------------| :---------------------| :----------------| :----------------|
                """.trimIndent()
    }


    fun getScheduleMarkdown(team: Team): String? {
        val builder = StringBuilder(200)
        for (match in team.getSchedule()) {
            builder.append("|").append(match.week)
            if (match.isDivisionalMatch()) {
                builder.append(" (div)")
            }
            builder.append("| ").append(match.homeTeam.name).append(" | ")
                .append(match.getAwayTeam().name).append(" |").append('\n')
        }
        builder.append("Home game count: ").append(team.getHomeGamesCount()).append('\n')
        builder.append('\n')
        return builder.toString()
    }


    fun getScheduleMarkdown(match: Match, week: Int): String? {
        val builder = StringBuilder(200)
        builder.append("|").append(week)
        if (match.isDivisionalMatch()) {
            builder.append(" (div)")
        }
        builder.append("| ").append(match.homeTeam.name).append(" | ").append(match.getAwayTeam().name)
            .append(" |").append(" |").append('\n')
        return builder.toString()
    }
}