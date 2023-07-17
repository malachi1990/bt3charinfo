package model.potara

data class Potara(val name: String,
    val type : String,
    val points : Short,
    val description : String,
    val useLimit : Short,
    val effects : List<PotaraEffect>) {

}