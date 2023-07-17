package service

import model.Build
import model.potara.Potara
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BuildCheckerTest {

    @Test
    fun testBuildCostRuleEmptyBuild(){
        val build = Build("Android 17", mutableListOf(), mutableListOf())
        BuildChecker().checkBuildCost(build)
        Assertions.assertTrue(build.buildErrors.isEmpty())
    }

    @Test
    fun testBuildCostRuleLegalBuild(){
        val potaras = mutableListOf(buildPotara("Attack +1", 1), buildPotara("Ultra Ego", 4), buildPotara("Ki Control", 2))
        val build = Build("Android 17", potaras, mutableListOf())
        BuildChecker().checkBuildCost(build)
        Assertions.assertTrue(build.buildErrors.isEmpty())
    }
    @Test
    fun testBuildCostRule8ptBuild(){
        val potaras = mutableListOf(buildPotara("Defense +2", 2), buildPotara("Ultra Ego", 4), buildPotara("Ki Control", 2))
        val build = Build("Android 17", potaras, mutableListOf())
        BuildChecker().checkBuildCost(build)
        Assertions.assertFalse(build.buildErrors.isEmpty())
        Assertions.assertEquals("Build is greater than 7 points.", build.buildErrors[0])
    }

    @Test
    fun testTooManyUses(){

    }

    private fun buildPotara(name : String, cost : Short) : Potara {
        return Potara(name, "", cost, "", 5, mutableListOf())
    }
}