package model.schedule

import java.util.*


class Match(var homeTeam: Team,
            var opposingTeam: Team,
            var week :Int
    ) {

    fun getAwayTeam(): Team {
        return opposingTeam
    }

    fun isDivisionalMatch(): Boolean {
        return homeTeam.division === getAwayTeam().division
    }

    fun getMatchDescription(): String {
        return homeTeam.name + " vs " + getAwayTeam().name + if (isDivisionalMatch()) " " + homeTeam.division.abbreviatedName else ""
    }

    fun getFullDescription(): String {
        return week.toString() + " " + homeTeam.name + " vs " + getAwayTeam().name + if (isDivisionalMatch()) " " + homeTeam.division.abbreviatedName else ""
    }


    fun flipHomeAndAway(): Match {
        return Match(homeTeam = opposingTeam, week = week, opposingTeam = homeTeam)
    }

    override fun toString(): String {
        return "Match{" +
                "homeTeam=" + homeTeam.name +
                ", week=" + week +
                ", opposingTeam=" + opposingTeam.name +
                '}'
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val match = o as Match
        return week == match.week && homeTeam == match.homeTeam && opposingTeam == match.opposingTeam
    }

    override fun hashCode(): Int {
        return Objects.hash(homeTeam, week, opposingTeam)
    }
}