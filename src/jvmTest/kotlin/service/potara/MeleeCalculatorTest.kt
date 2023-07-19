package service.potara

import model.Build
import model.potara.Potara
import model.potara.PotaraEffect
import model.potara.PotaraEffectType
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MeleeCalculatorTest {

    @Test
    fun testGrabDamage(){
        val build = Build("android 16", mutableListOf(buildAttack2(), buildMasterThrow()), mutableListOf() )
        val finalDamage = MeleeAttackCalculator().calculateGrabDamage(build, 2000, 0)
        assertEquals(5000, finalDamage) //off by 10 from the game
    }

    @Test
    fun testRushAttackDamage(){
        val build = Build("android 16", mutableListOf(buildAttack2()), mutableListOf() )
        val finalDamage = MeleeAttackCalculator().calculateRushAttackDamage(build, 330, 0)
        assertEquals(420, finalDamage) //off by 10 from the game
    }

    @Test
    fun testRushAttackDamageQFA(){
        val build = Build("android 16", mutableListOf(buildAttack2(), buildQFA()), mutableListOf() )
        val finalDamage = MeleeAttackCalculator().calculateRushAttackDamage(build, 330, 0)
        assertEquals(460, finalDamage)
    }


    private fun buildAttack2() : Potara {
        val effect = PotaraEffect(PotaraEffectType.ATTACK, "", 10)
        return Potara("Attack +2/Defense -1", "", 1, "", useLimit = 1, mutableListOf(effect))
    }

    private fun buildQFA() : Potara {
        val effect = PotaraEffect(PotaraEffectType.RUSH_ATTACK_UP, "", 0)
        return Potara("Quick Fast Attack", "", 1, "", useLimit = 1, mutableListOf(effect))
    }

    private fun buildMasterThrow() : Potara {
        val effect = PotaraEffect(PotaraEffectType.GRAB_UP, "", 0)
        return Potara("Master Throw", "", 1, "", useLimit = 2, mutableListOf(effect))
    }
}