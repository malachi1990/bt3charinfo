package model

data class Build(val characterName : String,
    val build : MutableList<Potara>,
    val buildErrors : MutableList<String>) {
}