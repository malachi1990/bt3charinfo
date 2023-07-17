package service

import model.Build
import model.Lineup
import model.potara.Potara
import java.util.*

class BuildChecker {

    fun checkSingleBuild(fighter : Build) : List<String>{
        val buildErrors = ArrayList<String>()

        return buildErrors
    }

    fun checkTeamBuild(teamLineup : Lineup){
        val buildErrors = ArrayList<String>()
        for(fighter in teamLineup.teamBuilds){
            buildErrors.addAll(checkSingleBuild(fighter))

        }
        checkTooManyUses(teamLineup)
        checkTeamSpecificRule(teamLineup)
        checkTeamHealingRules(teamLineup)
        if("Rugrats".equals(teamLineup.teamName, ignoreCase = true)){
            checkFusionRules(teamLineup)
        }

    }

    fun checkTooManyUses(teamLineup : Lineup){
        val allPotaras = mutableListOf<Potara>()
        teamLineup.teamBuilds.forEach {  fighterBuild -> allPotaras.addAll(fighterBuild.build)};
        val uniquePotaras = allPotaras.toSet()
        for (potara in uniquePotaras){

            if(Collections.frequency(uniquePotaras, potara) > potara.useLimit){
                teamLineup.teambuildErrors.add(potara.name + " has more than " + potara.useLimit + " uses")
            }
        }

    }

    val healingCharacters = listOf<String>("Cell", "Cell (Semi-Perfect)", "Frieza", "Frieza (2nd form)")

    fun checkTeamHealingRules(teamLineup : Lineup){
        // check for Cell, Frieza, Cooler, and Supreme Kai
    }

    fun checkTeamSpecificRule(teamLineup : Lineup){
        //check for team specific rules, like muscle/kaiju not being allowed a limiter

    }

    fun checkFusionRules(teamLineup : Lineup){
        //mostly to check if goten/trunks can legally fuse.
    }

    fun checkCharacterRules(fighter : Build){

    }

    fun checkBuildCost(fighter: Build){

        val buildCost = fighter.build.map(Potara::points).sum()
        if(buildCost > 7){
            fighter.buildErrors.add("Build is greater than 7 points.")
        }
    }
}