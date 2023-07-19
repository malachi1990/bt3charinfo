package service.potara

import model.Build
import model.potara.Potara
import model.potara.PotaraEffect
import model.potara.PotaraEffectType
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DefenseCalculatorTest{

    @Test
    fun testConvertToBlue(){
        val build = Build("Test char", mutableListOf(buildDefense2()), mutableListOf())
        assertEquals(2.0, DefenseLevelCalculator().convertToBlueLevel(build, 0, 0))
    }

    @Test
    fun testConvertToBlueInnateDefense(){
        val build = Build("Test char", mutableListOf(buildDefense2()), mutableListOf())
        assertEquals(3.2, DefenseLevelCalculator().convertToBlueLevel(build, 6, 0))
    }


    private fun buildDefense2() : Potara {
        val effect = PotaraEffect(PotaraEffectType.DEFENSE, "", 10)
        return Potara("Defense +2", "", 1, "", useLimit = 1, mutableListOf(effect))
    }

}
