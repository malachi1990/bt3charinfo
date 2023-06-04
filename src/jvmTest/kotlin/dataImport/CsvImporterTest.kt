package dataImport

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CsvImporterTest {

    @Test
    fun testTeamImporter(){
        val masterLists = CsvImporter().importTeamData()
        Assertions.assertEquals("Androids", masterLists[0].name)
    }

    @Test
    fun testB1DataImporter(){
        val b1s = CsvImporter().importB1Data()
        Assertions.assertEquals(163*2, b1s.size)
        Assertions.assertEquals("Adult Gohan", b1s[0].characterName)
        Assertions.assertEquals("Wild Sense", b1s[0].name)
        Assertions.assertEquals("Adult Gohan", b1s[1].characterName)
        Assertions.assertEquals("Full Power Charge", b1s[1].name)
        Assertions.assertEquals("Android 21", b1s.last().characterName)
        Assertions.assertEquals("Chocolate Delight", b1s.last().name)

    }

    @Test
    fun testB2DataImporter(){
        val b2s = CsvImporter().importB2Data()
        Assertions.assertEquals("Adult Gohan", b2s[0].characterName)
        Assertions.assertEquals("Explosive Madan", b2s[0].name)
        Assertions.assertEquals("Adult Gohan", b2s[1].characterName)
        Assertions.assertEquals("Full Power Energy Blast Volley", b2s[1].name)
        Assertions.assertEquals("Adult Gohan", b2s[2].characterName)
        Assertions.assertEquals("Super Kamehameha", b2s[2].name)
        Assertions.assertEquals(6560, b2s[3].damage)

    }

    @Test
    fun testUniqueCharacteristicsImporter(){
        val characteristics = CsvImporter().importUniqueCharacteristics()
        Assertions.assertEquals("Android 13", characteristics[0].characterName)
        Assertions.assertEquals( 8, characteristics[0].uniqueCharacteristics.size)
        Assertions.assertTrue(characteristics[0].uniqueCharacteristics.last().isEmpty())
    }
}