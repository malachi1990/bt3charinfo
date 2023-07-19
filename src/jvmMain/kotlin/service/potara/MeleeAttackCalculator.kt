package service.potara

import model.Build
import model.potara.PotaraEffectType

class MeleeAttackCalculator {

    fun calculateGrabDamage(build: Build, baseGrabDamage : Int, barsMissing : Int) : Int{
        val blueModifier= calculateBlueModifier(PotaraEffectType.ATTACK, build.build)
        val scalingModifier = scalingCalculator(barsMissing)
        var damage = baseGrabDamage  + (baseGrabDamage * (blueModifier + scalingModifier) * 0.025)
        if(build.hasPotaraEffect(PotaraEffectType.GRAB_UP)){
            damage *= 2
        }
        return damage.toInt()
    }

    fun calculateRushAttackDamage(build: Build, baseDamage : Int, barsMissing : Int) : Int{
        val blueModifier= calculateBlueModifier(PotaraEffectType.ATTACK, build.build)
        val scalingModifier = scalingCalculator(barsMissing)
        var damage = baseDamage  + (baseDamage * (blueModifier + scalingModifier) * 0.025)
        if(build.hasPotaraEffect(PotaraEffectType.RUSH_ATTACK_UP)){
            damage *= 1.1
        }
        if(build.hasPotaraEffect(PotaraEffectType.RUSH_ATTACK_DOWN)){
            damage *= 0.9
        }

        return (damage + (10 - damage % 10)).toInt()
    }
}