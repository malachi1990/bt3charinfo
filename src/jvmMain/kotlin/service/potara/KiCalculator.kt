package service.potara

import model.Build
import model.potara.PotaraEffectType

class KiCalculator {

    fun calculateActiveKi(build : Build, fighterActiveKi : Int, barsMissing : Int) : Int {
        val blueModifier= calculateBlueModifier(PotaraEffectType.KI, build.build)
        val scalingModifier = scalingCalculator(barsMissing)
        val modifiedDamage = fighterActiveKi * ((blueModifier + scalingModifier) * 0.025)
        return 0
    }

    fun calculatePassiveKi(build : Build, fighterPassiveKi : Int, barsMissing : Int) : Int {
        val blueModifier= calculateBlueModifier(PotaraEffectType.KI, build.build)
        val scalingModifier = scalingCalculator(barsMissing)
        var modifiedKi = (fighterPassiveKi + fighterPassiveKi * ((blueModifier + scalingModifier) * 0.025)).toInt()

        if(build.hasPotaraEffect(PotaraEffectType.KI_REGEN)){
            val regenScale =  getScale(build, PotaraEffectType.KI_REGEN)
            modifiedKi += regenScale
        }
        if(build.hasPotaraEffect(PotaraEffectType.PASSIVE_KI)){
            val regenScale =  getScale(build, PotaraEffectType.PASSIVE_KI)
            modifiedKi += regenScale
        }
        return modifiedKi
    }

    fun getScale(build :Build, potaraEffectType: PotaraEffectType) : Int {


        return build.getScale(potaraEffectType)
    }

}