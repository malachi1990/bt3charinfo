package service.schedule.writer

import model.schedule.Match
import model.schedule.Team
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.util.function.Consumer


class TextWriter {

    fun writeToFile(seasonSchedule: Map<Int, List<Match>>, allTeams: List<Team>, filePath: String) {
        println("Writing schedule to plain text file.")
        val builder = StringBuilder()
        allTeams.forEach(Consumer { team: Team -> builder.append(team.printSchedule()) })
        seasonSchedule.forEach { (week: Int?, matches: List<Match>) ->
            builder.append("Week ").append(week).append('\n')
            matches.forEach { match ->
                builder.append(match.getMatchDescription()).append(", ")
            }
            builder.append('\n')
        }
        try {
            Files.writeString(
                Paths.get(filePath),
                builder.toString(),
                StandardOpenOption.APPEND,
                StandardOpenOption.CREATE
            )
        } catch (e: Exception) {
            println("unable to write to file: " + e.localizedMessage)
        }
    }
}