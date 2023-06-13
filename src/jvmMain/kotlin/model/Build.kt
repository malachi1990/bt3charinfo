package model

data class Build(val characterName : String,
    val build : MutableList<Potara>,
    val buildErrors : MutableList<String>) {

    fun hasPotara(potara : Potara) : Boolean {
        return build.contains(potara)
    }
}