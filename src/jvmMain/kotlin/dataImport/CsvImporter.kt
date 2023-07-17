package dataImport

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import model.*
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.text.NumberFormat
import java.util.*

class CsvImporter {
    val damageFormatter = NumberFormat.getNumberInstance(Locale.US)
    fun importCharacterNames(fileName: String){

    }

    fun importTeamData()  : List<MasterList>{

        val fileContent = javaClass.getResource("/info/Teams.csv")?.readText()
        val masterLists = mutableListOf<MasterList>()
        if (fileContent != null) {
            val teams = fileContent.split("\\r?\\n")
            for(team in teams){
                val parts = team.split(",")
                masterLists.add(MasterList(parts[0], parts.subList(1, parts.size)))
            }
        }
        return masterLists
    }

    fun importB1Data() : List<B1Move>{
        val fileContent = File(javaClass.getResource("/info/B1List.csv")!!.toURI() )
        val b1s = mutableListOf<B1Move>()
        csvReader().open(fileContent) {
            readAllAsSequence().forEach { row: List<String> ->
                //Do something
                //println(row) //[a, b, c]
                b1s.add(B1Move(row[0], row[1], Integer.valueOf(row[2]), row[3], row[4]))
                b1s.add(B1Move(row[0], row[5], Integer.valueOf(row[6]), row[7], row[8]))
            }
        }
        return b1s
    }

    fun importB2Data() : List<B2Move>{
        val fileContent = File(javaClass.getResource("/info/B2List.csv")!!.toURI())
        val b2s = mutableListOf<B2Move>()
        csvReader().open(fileContent) {
            readAllAsSequence().forEach { row: List<String> ->
                //Do something
                //println(row)
                val firstB2Cost = damageFormatter.parse(row[2]).toInt()
                val firstB2Damage = damageFormatter.parse(row[3]).toInt()
                val firstB2DamageBoosted = damageFormatter.parse(row[4]).toInt()

                val secondB2Cost = damageFormatter.parse(row[8]).toInt()
                val secondB2Damage = damageFormatter.parse(row[9]).toInt()
                val secondB2DamageBoosted = damageFormatter.parse(row[10]).toInt()

                val thirdB2Cost = damageFormatter.parse(row[14]).toInt()
                val thirdB2Damage = damageFormatter.parse(row[15]).toInt()
                val thirdB2DamageBoosted = damageFormatter.parse(row[16]).toInt()

                b2s.add(B2Move(row[0], row[1], firstB2Cost, firstB2Damage, firstB2DamageBoosted, row[5], row[6], false))
                b2s.add(B2Move(row[0], row[7], secondB2Cost, secondB2Damage, secondB2DamageBoosted, row[11], row[12], false))
                b2s.add(B2Move(row[0], row[13], thirdB2Cost, thirdB2Damage, thirdB2DamageBoosted, row[17], row[18], true))

            }
        }
        return b2s

    }
    fun importPlayerStats() : List<PlayerBaseStats>{
        val fileContent = File(javaClass.getResource("/info/PlayerBaseStats.csv")!!.toURI())
        val baseStats = mutableListOf<PlayerBaseStats>()
        csvReader().open(fileContent) {
            readAllAsSequence().forEach { row: List<String> ->
                //Do something
                //println(row)

                baseStats.add(PlayerBaseStats(name = row[0], baseHP = row[1].toInt(),
                    defenseLevel = row[2].toFloat(), baseRushMeleeDamage = row[3].toInt(),
                    baseSmashMeleeDamage = row[4].toInt(), baseRushBlastDamage = row[5].toInt(),
                    rushBlastVolleyCount = row[6].toInt(), baseVolleyDamage = row[7].toInt(),
                    baseChargedBlastDamage = row[8].toInt(), baseThrowDamage = row[9].toInt(),
                    baseActiveCharge = row[10].toInt(), basePassiveCharge = row[11].toInt(),
                    baseKiPower = row[12].toInt(), baseB1charge = row[13].toInt(),
                    maxblastStocks = row[14].toInt(), baseSwitchGaugeCharge = row[15].toInt(),
                    baseEvilness = row[16].toInt(), baseMpmDuration = row[17].toInt(),
                    baseVanishingAttackCount = row[18].toInt(), baseDragonDashCount = row[19].toInt(),
                    baseUnderwaterActiveCharge = row[20].toInt()))

            }
        }
        return baseStats
    }

    fun importUniqueCharacteristics() : List<PlayerUniqueCharacteristics> {
        val fileContent = File(javaClass.getResource("/info/PlayerUniqueCharacteristics.csv")!!.toURI() )
        val parsed = mutableListOf<PlayerUniqueCharacteristics>()
        csvReader().open(fileContent) {
            readAllAsSequence().forEach { row: List<String> ->
                //println(row) //[a, b, c]
                val name = row[0]
                val characteristics = mutableListOf<String>()
                characteristics.addAll(row.subList(1, row.size))
                parsed.add(PlayerUniqueCharacteristics(name, characteristics))

            }
        }
        return parsed

    }

    fun importPotaras(fileName: String){
        val b1Data = Files.readAllLines(Paths.get("Potara.csv"))

    }
}