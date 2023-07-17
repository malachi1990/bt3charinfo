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

    private fun buildAttack2() : Potara {
        val effect = PotaraEffect(PotaraEffectType.ATTACK, "", 10)
        return Potara("Attack +2/Defense -1", "", 1, "", useLimit = 1, mutableListOf(effect))
    }

    private fun buildMasterThrow() : Potara {
        val effect = PotaraEffect(PotaraEffectType.GRAB_UP, "", 0)
        return Potara("Master Throw", "", 1, "", useLimit = 2, mutableListOf(effect))
    }
}