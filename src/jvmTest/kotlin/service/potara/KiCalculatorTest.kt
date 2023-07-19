package service.potara

import model.Build
import model.potara.Potara
import model.potara.PotaraEffect
import model.potara.PotaraEffectType
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class KiCalculatorTest {

    @Test
    fun testGetScale(){
        val build = Build("android 17", mutableListOf(buildHighTension()), mutableListOf() )

        assertEquals(900, KiCalculator().getScale(build, PotaraEffectType.KI_REGEN))
    }

    @Test
    fun testGetScaleConflict(){
        val build = Build("android 17", mutableListOf(buildHighTension(), buildTensionUp()), mutableListOf() )

        assertEquals(900, KiCalculator().getScale(build, PotaraEffectType.KI_REGEN))
    }

    @Test
    fun testCalculatePassiveKi(){
        val build = Build("android 17", mutableListOf(buildHighTension(), buildKi2()), mutableListOf() )

        assertEquals(5900, KiCalculator().calculatePassiveKi(build, 4000, 0))
    }

    private fun buildKi2() : Potara {
        val effect = PotaraEffect(PotaraEffectType.KI,"", 10)
        return Potara("Ki +2", "", 1, "", useLimit = 1, mutableListOf(effect))
    }
    private fun buildHighTension() : Potara {
        val effect = PotaraEffect(PotaraEffectType.KI_REGEN, "", 900)
        return Potara("High Tension", "", 3, "", useLimit = 2, mutableListOf(effect))
    }
    private fun buildTensionUp() : Potara {
        val effect = PotaraEffect(PotaraEffectType.KI_REGEN, "", 600)
        return Potara("Tension Up", "", 2, "", useLimit = 2, mutableListOf(effect))
    }

    private fun buildHatredOfSaiyans() : Potara {
        val effect = PotaraEffect(PotaraEffectType.PASSIVE_KI, "", 300)
        return Potara("Hatred of Saiyans", "", 1, "", useLimit = 2, mutableListOf(effect))
    }


}