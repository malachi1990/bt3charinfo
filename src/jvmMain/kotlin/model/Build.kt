package model

import model.potara.Potara
import model.potara.PotaraEffectType

data class Build(val characterName : String,
                 val build : MutableList<Potara>,
                 val buildErrors : MutableList<String>) {

    fun hasPotara(potara : Potara) : Boolean {
        return build.contains(potara)
    }

    fun hasPotaraEffect(effect : PotaraEffectType) : Boolean{
        build.forEach { potara ->
            potara.effects.forEach {
                if (it.type == effect) {
                    return true
                }
            }
        }
        return false
    }

    fun getScale(effect: PotaraEffectType): Int {
        var intScales = mutableListOf<Int>()
        build.forEach{ potara ->
            intScales.add(potara.getScale(effect))
        }
        intScales.removeIf{ scale -> scale == 0}
        intScales.sortWith(reverseOrder())
        if(intScales.size > 0) {
            return intScales[0]
        }

        return 0
    }
}