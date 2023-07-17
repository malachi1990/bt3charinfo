package service.potara

import model.B2Move
import model.Build
import model.potara.PotaraEffectType
import kotlin.math.roundToInt

class B2Calculator {

    fun calculateB2Damage(fighterBuild : Build, b2 :B2Move, barsMissing : Int) : Int {
        val blueModifier= calculateBlueModifier(PotaraEffectType.SUPER, fighterBuild.build)
        val scalingModifier = scalingCalculator(barsMissing)
        val modifiedDamage = b2.damage * ((blueModifier + scalingModifier) * 0.025)

        var damage = b2.damage + modifiedDamage

        if(b2.isUltimate && fighterBuild.hasPotaraEffect(PotaraEffectType.ULTIMATE)){
            damage *= 1.2
        }

        return damage.roundToInt()
    }
}