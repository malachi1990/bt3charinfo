package model

class Lineup(val teamName : String,
    val teamBuilds : MutableList<Build>,
    val teambuildErrors : MutableList<String>) {
}