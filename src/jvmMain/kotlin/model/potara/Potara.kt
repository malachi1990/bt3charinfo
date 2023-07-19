package model.potara

data class Potara(val name: String,
    val type : String,
    val points : Short,
    val description : String,
    val useLimit : Short,
    val effects : List<PotaraEffect>) {

    fun hasEffect(effectType : PotaraEffectType) : Boolean{
        return effects.stream().anyMatch{ effect : PotaraEffect -> effect.type == effectType}
    }

    fun getScale(effectType: PotaraEffectType): Int{
        val filteredEffects = effects.find{effect -> effect.hasType(effectType)}

        if(filteredEffects != null){
            return filteredEffects.intScale
        }
        return 0;
    }
}