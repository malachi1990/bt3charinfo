package model.benching

data class BenchCheckerDTO(val fighter1 : String,
                           val fighter2 : String,
                           val fighter3 : String,
                           val fighter4 : String,
                           val fighter5 : String,
                           val freeAgent : String,
                           val weeklyBenches : List<String>,
    val faBenches : List<String>
    )
