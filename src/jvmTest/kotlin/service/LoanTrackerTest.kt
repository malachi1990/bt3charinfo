package service

import dataImport.CsvImporter
import model.MasterList
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoanTrackerTest {

    private var rosters = listOf<MasterList>()
    @BeforeAll
    fun readMasterList(){
        rosters = CsvImporter().importTeamData()
    }

    @Test
    fun testLoanTracking(){
        val result = LoanTracker().findRosters("Android 16", rosters)
        assertEquals(1, result.size)
    }

    @Test
    fun testMultiTeamTracking(){
        val result = LoanTracker().findRosters("Cell", rosters)
        assertEquals(4, result.size)
    }
}