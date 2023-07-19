package service.potara

import model.Build
import model.potara.PotaraEffectType

class DefenseLevelCalculator {

    fun convertToBlueLevel(build : Build, fighterDefenseLevel : Int, barsMissing : Int) : Double{
        val blueModifier= calculateBlueModifier(PotaraEffectType.DEFENSE, build.build)
        val scalingModifier = scalingCalculator(barsMissing, fighterDefenseLevel)
        return (blueModifier + scalingModifier).toDouble()/5.0
    }


}