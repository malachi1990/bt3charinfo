package service.potara

import model.B2Move
import model.Build
import model.potara.Potara
import model.potara.PotaraEffect
import model.potara.PotaraEffectType
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class B2CalculatorTest {

    @Test
    fun testB2Calculator(){
        val build = Build("android 17", mutableListOf(buildSuper2()), mutableListOf() )
        val finalDamage = B2Calculator().calculateB2Damage(build, buildB2(), 0)
        assertEquals(6850, finalDamage) //off by 10 from the game
    }

    @Test
    fun testSuper2UltBuild(){
        val build = Build("android 17", mutableListOf(buildSuper2(), buildUltimate()), mutableListOf() )
        val finalDamage = B2Calculator().calculateB2Damage(build, buildUltimateMove(), 0)
        assertEquals(20460, finalDamage) //off by 10 from the game
    }

    private fun buildSuper2() : Potara {
        val effect = PotaraEffect(PotaraEffectType.SUPER, "", 10)
        return Potara("Super +2/Ki -1", "", 1, "", useLimit = 1, mutableListOf(effect))
    }

    private fun buildUltimate() : Potara {
        val effect = PotaraEffect(PotaraEffectType.ULTIMATE, "", 0)
        return Potara("Ultimate +1", "", 2, "", useLimit = 1, mutableListOf(effect))
    }
    private fun buildB2() : B2Move {

        return B2Move("Android 17",
            "Photon Flash",
            2,
            5480,
            6080,
            "beam",
            "full power beam",
            true)
    }

    private fun buildUltimateMove() : B2Move {

        return B2Move("Android 17",
            "Sadistic Dance",
            5,
            13640,
            15010,
            "Dash",
            "Rush Ult",
            true)
    }
}