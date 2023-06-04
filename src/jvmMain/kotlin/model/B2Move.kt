package model

data class B2Move(val characterName : String,
                  val name : String,
                  val cost : Int,
                  val damage : Int,
                  val boostedDamage: Int,
                  val type : String,
                  val description : String) {
}