package model

data class Bt3Character (
    val name : String,
    val baseStats: PlayerBaseStats,
    val firstB1 : B1Move,
    val secondB1 : B1Move,

    val firstB2 : B2Move,
    val secondB2 : B2Move,
    val ultimate : B2Move


){
}