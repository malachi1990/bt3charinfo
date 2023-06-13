package service

import model.benching.Bench
import model.benching.SeasonBenches
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class BenchCheckerTest {

    @Test
    fun testHappyPath() {
        val fighters: List<String> =
            ArrayList(listOf("Android 16", "Android 17", "Cell", "Super 17", "Android 19", "Tapion"))
        val weeklyLineups: MutableList<Bench> = ArrayList()
        weeklyLineups.add(Bench(1, "Android 16", "Tapion"))
        weeklyLineups.add(Bench(2, "Android 17", "Tapion"))
        weeklyLineups.add(Bench(3, "Cell", "Tapion"))
        weeklyLineups.add(Bench(4, "Super 17", "Tapion"))
        weeklyLineups.add(Bench(5, "Android 19", "Tapion"))
        weeklyLineups.add(Bench(6, "Android 16", "Tapion"))
        weeklyLineups.add(Bench(7, "Android 17", "Tapion"))
        weeklyLineups.add(Bench(8, "Cell", "Tapion"))
        weeklyLineups.add(Bench(9, "Super 17", "Tapion"))
        weeklyLineups.add(Bench(10, "Android 19", "Tapion"))
        weeklyLineups.add(Bench(11, "Android 16", "Tapion"))
        weeklyLineups.add(Bench(12, "Android 17", "Tapion"))
        weeklyLineups.add(Bench(13, "Cell", "Tapion"))
        weeklyLineups.add(Bench(14, "Super 17", "Tapion"))
        weeklyLineups.add(Bench(15, "Android 19", "Tapion"))
        val seasonBenches = SeasonBenches(fighters, weeklyLineups)
        val objUnderTest = BenchChecker()
        val messages: List<String> = objUnderTest.validateBenches(seasonBenches)
        Assertions.assertTrue(messages.isEmpty())
    }

    @Test
    fun testFails12WeekRule() {
        val fighters: List<String> =
            ArrayList(listOf("Android 16", "Android 17", "Cell", "Super 17", "Android 19", "Tapion"))
        val weeklyLineups: MutableList<Bench> = ArrayList()
        weeklyLineups.add(Bench(1, "Android 16", "Tapion"))
        weeklyLineups.add(Bench(2, "Android 17", "Tapion"))
        weeklyLineups.add(Bench(3, "Cell", "Tapion"))
        weeklyLineups.add(Bench(4, "Super 17", "Tapion"))
        weeklyLineups.add(Bench(5, "Android 19", "Tapion"))
        weeklyLineups.add(Bench(6, "Android 16", "Tapion"))
        weeklyLineups.add(Bench(7, "Android 17", "Tapion"))
        weeklyLineups.add(Bench(8, "Cell", "Tapion"))
        weeklyLineups.add(Bench(9, "Super 17", "Tapion"))
        weeklyLineups.add(Bench(10, "Android 19", "Tapion"))
        weeklyLineups.add(Bench(11, "Android 16", "Tapion"))
        weeklyLineups.add(Bench(12, "Android 17", "Tapion"))
        weeklyLineups.add(Bench(13, "Cell", "Tapion"))
        weeklyLineups.add(Bench(14, "Super 17", "Tapion"))
        weeklyLineups.add(Bench(15, "Cell", "Tapion"))
        val seasonBenches = SeasonBenches(fighters, weeklyLineups)
        val objUnderTest = BenchChecker()
        val messages: List<String> = objUnderTest.validateBenches(seasonBenches)
        Assertions.assertTrue(messages.contains("Android 19 is in more than 12 weeks."))
    }

    @Test
    fun testFreeAgentBenched3Weeks() {
        val fighters: List<String> =
            ArrayList(listOf("Android 16", "Android 17", "Cell", "Super 17", "Android 19", "Tapion"))
        val weeklyLineups: MutableList<Bench> = ArrayList()
        weeklyLineups.add(Bench(1, "Android 16", "Tapion"))
        weeklyLineups.add(Bench(2, "Android 17", "Tapion"))
        weeklyLineups.add(Bench(3, "Cell", "Tapion"))
        weeklyLineups.add(Bench(4, "Super 17", "Tapion"))
        weeklyLineups.add(Bench(5, "Android 19", "Tapion"))
        weeklyLineups.add(Bench(6, "Android 16", "Android 19"))
        weeklyLineups.add(Bench(7, "Android 17", "Android 19"))
        weeklyLineups.add(Bench(8, "Cell", "Android 19"))
        weeklyLineups.add(Bench(9, "Super 17", "Android 19"))
        weeklyLineups.add(Bench(10, "Tapion", "Android 19"))
        weeklyLineups.add(Bench(11, "Android 16", "Android 19"))
        weeklyLineups.add(Bench(12, "Android 17", "Android 19"))
        weeklyLineups.add(Bench(13, "Cell", "Android 19"))
        weeklyLineups.add(Bench(14, "Super 17", "Android 19"))
        weeklyLineups.add(Bench(15, "Tapion", "Android 19"))
        val seasonBenches = SeasonBenches(fighters, weeklyLineups)
        val objUnderTest = BenchChecker()
        val messages: List<String> = objUnderTest.validateBenches(seasonBenches)
        Assertions.assertTrue(messages.isEmpty())
    }

    @Test
    fun testFreeAgentBenched4Weeks() {
        val fighters: List<String> =
            ArrayList(listOf("Android 16", "Android 17", "Cell", "Super 17", "Android 19", "Tapion"))
        val weeklyLineups: MutableList<Bench> = ArrayList()
        weeklyLineups.add(Bench(1, "Android 16", "Tapion"))
        weeklyLineups.add(Bench(2, "Android 17", "Tapion"))
        weeklyLineups.add(Bench(3, "Cell", "Tapion"))
        weeklyLineups.add(Bench(4, "Super 17", "Tapion"))
        weeklyLineups.add(Bench(5, "Android 19", "Tapion"))
        weeklyLineups.add(Bench(6, "Android 16", "Android 19"))
        weeklyLineups.add(Bench(7, "Android 17", "Android 19"))
        weeklyLineups.add(Bench(8, "Tapion", "Android 19"))
        weeklyLineups.add(Bench(9, "Super 17", "Android 19"))
        weeklyLineups.add(Bench(10, "Tapion", "Android 19"))
        weeklyLineups.add(Bench(11, "Android 16", "Android 19"))
        weeklyLineups.add(Bench(12, "Android 17", "Android 19"))
        weeklyLineups.add(Bench(13, "Cell", "Android 19"))
        weeklyLineups.add(Bench(14, "Super 17", "Android 19"))
        weeklyLineups.add(Bench(15, "Tapion", "Android 19"))
        val seasonBenches = SeasonBenches(fighters, weeklyLineups)
        val objUnderTest = BenchChecker()
        val messages: List<String> = objUnderTest.validateBenches(seasonBenches)
        Assertions.assertTrue(messages.contains("Android 19 has more than 3 benches"))
    }

    @Test
    fun test2FreeAgentBenchSwaps() {
        val fighters: List<String> =
            ArrayList(listOf("Android 16", "Android 17", "Cell", "Super 17", "Android 19", "Tapion"))
        val weeklyLineups: MutableList<Bench> = ArrayList()
        weeklyLineups.add(Bench(1, "Android 16", "Tapion"))
        weeklyLineups.add(Bench(2, "Android 17", "Tapion"))
        weeklyLineups.add(Bench(3, "Cell", "Tapion"))
        weeklyLineups.add(Bench(4, "Super 17", "Tapion"))
        weeklyLineups.add(Bench(5, "Android 19", "Tapion"))
        weeklyLineups.add(Bench(6, "Android 16", "Android 19"))
        weeklyLineups.add(Bench(7, "Android 17", "Android 19"))
        weeklyLineups.add(Bench(8, "Cell", "Android 19"))
        weeklyLineups.add(Bench(9, "Super 17", "Android 19"))
        weeklyLineups.add(Bench(10, "Tapion", "Android 19"))
        weeklyLineups.add(Bench(11, "Android 16", "Cell"))
        weeklyLineups.add(Bench(12, "Android 17", "Cell"))
        weeklyLineups.add(Bench(13, "Android 19", "Cell"))
        weeklyLineups.add(Bench(14, "Super 17", "Cell"))
        weeklyLineups.add(Bench(15, "Tapion", "Cell"))
        val seasonBenches = SeasonBenches(fighters, weeklyLineups)
        val objUnderTest = BenchChecker()
        val messages: List<String> = objUnderTest.validateBenches(seasonBenches)
        Assertions.assertTrue(messages.isEmpty())
    }

    @Test
    fun test2FreeAgentBenchSwapsMultipleFaBenches() {
        val fighters: List<String> =
            ArrayList(listOf("Android 16", "Android 17", "Cell", "Super 17", "Android 19", "Tapion"))
        val weeklyLineups: MutableList<Bench> = ArrayList()
        weeklyLineups.add(Bench(1, "Android 16", "Tapion"))
        weeklyLineups.add(Bench(2, "Android 17", "Tapion"))
        weeklyLineups.add(Bench(3, "Cell", "Tapion"))
        weeklyLineups.add(Bench(4, "Super 17", "Tapion"))
        weeklyLineups.add(Bench(5, "Android 19", "Tapion"))
        weeklyLineups.add(Bench(6, "Android 16", "Android 19"))
        weeklyLineups.add(Bench(7, "Android 17", "Android 19"))
        weeklyLineups.add(Bench(8, "Tapion", "Android 19"))
        weeklyLineups.add(Bench(9, "Super 17", "Android 19"))
        weeklyLineups.add(Bench(10, "Tapion", "Android 19"))
        weeklyLineups.add(Bench(11, "Android 16", "Cell"))
        weeklyLineups.add(Bench(12, "Android 17", "Cell"))
        weeklyLineups.add(Bench(13, "Tapion", "Cell"))
        weeklyLineups.add(Bench(14, "Super 17", "Cell"))
        weeklyLineups.add(Bench(15, "Tapion", "Cell"))
        val seasonBenches = SeasonBenches(fighters, weeklyLineups)
        val objUnderTest = BenchChecker()
        val messages: List<String> = objUnderTest.validateBenches(seasonBenches)
        Assertions.assertTrue(messages.isEmpty())
    }

    @Test
    fun testConsecutiveLineups() {
        val fighters: List<String> =
            ArrayList(listOf("Android 16", "Android 17", "Cell", "Super 17", "Android 19", "Tapion"))
        val weeklyLineups: MutableList<Bench> = ArrayList()
        weeklyLineups.add(Bench(1, "Android 16", "Tapion"))
        weeklyLineups.add(Bench(2, "Tapion", "Android 16"))
        weeklyLineups.add(Bench(3, "Cell", "Tapion"))
        weeklyLineups.add(Bench(4, "Super 17", "Tapion"))
        weeklyLineups.add(Bench(5, "Android 19", "Tapion"))
        val seasonBenches = SeasonBenches(fighters, weeklyLineups)
        val objUnderTest = BenchChecker()
        val messages: List<String> = objUnderTest.validateBenches(seasonBenches)
        Assertions.assertTrue(messages.contains("week 1 and 2 have the same lineup. Back to back identical lineups are not allowed"))
    }

    @Test
    fun sampleHybridsTest() {
        val fighters: List<String> =
            ArrayList(listOf("Kid Gohan", "Ultimate Gohan", "Future Gohan", "Sword Trunks", "Teen Gohan", "Nam"))
        val weeklyLineups: MutableList<Bench> = ArrayList()
        weeklyLineups.add(Bench(1, "Ultimate Gohan", "Kid Gohan"))
        weeklyLineups.add(Bench(2, "Future Gohan", "Kid Gohan"))
        weeklyLineups.add(Bench(3, "Ultimate Gohan", "Kid Gohan"))
        weeklyLineups.add(Bench(4, "Sword Trunks", "Kid Gohan"))
        weeklyLineups.add(Bench(5, "Ultimate Gohan", "Kid Gohan"))
        weeklyLineups.add(Bench(6, "Future Gohan", "Kid Gohan"))
        weeklyLineups.add(Bench(7, "Sword Trunks", "Kid Gohan"))
        weeklyLineups.add(Bench(8, "Nam", "Kid Gohan"))
        weeklyLineups.add(Bench(9, "Teen Gohan", "Kid Gohan"))
        weeklyLineups.add(Bench(10, "Nam", "Kid Gohan"))
        weeklyLineups.add(Bench(11, "Teen Gohan", "Kid Gohan"))
        weeklyLineups.add(Bench(12, "Future Gohan", "Kid Gohan"))
        weeklyLineups.add(Bench(13, "Teen Gohan", "Kid Gohan"))
        weeklyLineups.add(Bench(14, "Nam", "Kid Gohan"))
        weeklyLineups.add(Bench(15, "Sword Trunks", "Kid Gohan"))
        val seasonBenches = SeasonBenches(fighters, weeklyLineups)
        val objUnderTest = BenchChecker()
        val messages: List<String> = objUnderTest.validateBenches(seasonBenches)
        Assertions.assertTrue(messages.isEmpty())
    }

    @Test
    fun hybridsBasedTest() {
        val fighters: List<String> =
            ArrayList(listOf("Kid Gohan", "Ultimate Gohan", "Future Gohan", "Sword Trunks", "Teen Gohan", "Nam"))
        val weeklyLineups: MutableList<Bench> = ArrayList()
        weeklyLineups.add(Bench(1, "Ultimate Gohan", "Kid Gohan"))
        weeklyLineups.add(Bench(2, "Future Gohan", "Kid Gohan"))
        weeklyLineups.add(Bench(3, "Ultimate Gohan", "Kid Gohan"))
        weeklyLineups.add(Bench(4, "Sword Trunks", "Kid Gohan"))
        weeklyLineups.add(Bench(5, "Ultimate Gohan", "Kid Gohan"))
        weeklyLineups.add(Bench(6, "Sword Trunks", "Ultimate Gohan"))
        weeklyLineups.add(Bench(7, "Kid Gohan", "Ultimate Gohan"))
        weeklyLineups.add(Bench(8, "Nam", "Ultimate Gohan"))
        weeklyLineups.add(Bench(9, "Teen Gohan", "Ultimate Gohan"))
        weeklyLineups.add(Bench(10, "Nam", "Ultimate Gohan"))
        weeklyLineups.add(Bench(11, "Teen Gohan", "Kid Gohan"))
        weeklyLineups.add(Bench(12, "Future Gohan", "Kid Gohan"))
        weeklyLineups.add(Bench(13, "Teen Gohan", "Kid Gohan"))
        weeklyLineups.add(Bench(14, "Nam", "Kid Gohan"))
        weeklyLineups.add(Bench(15, "Sword Trunks", "Kid Gohan"))
        val seasonBenches = SeasonBenches(fighters, weeklyLineups)
        val objUnderTest = BenchChecker()
        val messages: List<String> = objUnderTest.validateBenches(seasonBenches)
        Assertions.assertTrue(messages.contains("Ultimate Gohan has more than 3 benches"))
    }

    @Test
    fun cinemaBasedTest() {
        val fighters: List<String> = ArrayList(listOf("Garlic Jr", "Gogeta", "Janemba", "Fasha", "Turles", "Frieza"))
        val weeklyLineups: MutableList<Bench> = ArrayList()
        weeklyLineups.add(Bench(1, "Turles", "Garlic Jr"))
        weeklyLineups.add(Bench(2, "Frieza", "Garlic Jr"))
        weeklyLineups.add(Bench(3, "Gogeta", "Garlic Jr"))
        weeklyLineups.add(Bench(4, "Janemba", "Garlic Jr"))
        weeklyLineups.add(Bench(5, "Turles", "Garlic Jr"))
        weeklyLineups.add(Bench(6, "Gogeta", "Fasha"))
        weeklyLineups.add(Bench(7, "Garlic Jr", "Fasha"))
        weeklyLineups.add(Bench(8, "Janemba", "Fasha"))
        weeklyLineups.add(Bench(9, "Turles", "Garlic Jr"))
        weeklyLineups.add(Bench(10, "Frieza", "Garlic Jr"))
        weeklyLineups.add(Bench(11, "Janemba", "Frieza"))
        weeklyLineups.add(Bench(12, "Fasha", "Frieza"))
        weeklyLineups.add(Bench(13, "Gogeta", "Frieza"))
        weeklyLineups.add(Bench(14, "Garlic Jr", "Frieza"))
        weeklyLineups.add(Bench(15, "Fasha", "Frieza"))
        val seasonBenches = SeasonBenches(fighters, weeklyLineups)
        val objUnderTest = BenchChecker()
        val messages: List<String> = objUnderTest.validateBenches(seasonBenches)
        Assertions.assertTrue(messages.contains("Garlic Jr has more than 3 benches"))
    }

    @Test
    fun budokaiBasedTest() {
        val fighters: List<String> =
            ArrayList(listOf("early goku", "end goku", "kid goku", "cyborg tao", "Videl", "Nam"))
        val weeklyLineups: MutableList<Bench> = ArrayList()
        weeklyLineups.add(Bench(1, "Nam", "Videl"))
        weeklyLineups.add(Bench(2, "kid goku", "Videl"))
        weeklyLineups.add(Bench(3, "Nam", "Videl"))
        weeklyLineups.add(Bench(4, "kid goku", "Videl"))
        weeklyLineups.add(Bench(5, "Nam", "Videl"))
        weeklyLineups.add(Bench(6, "kid goku", "Nam"))
        weeklyLineups.add(Bench(7, "cyborg tao", "Nam"))
        weeklyLineups.add(Bench(8, "early goku", "Nam"))
        weeklyLineups.add(Bench(9, "cyborg tao", "Nam"))
        weeklyLineups.add(Bench(10, "end goku", "Nam"))
        weeklyLineups.add(Bench(11, "early goku", "Nam"))
        weeklyLineups.add(Bench(12, "end goku", "Nam"))
        weeklyLineups.add(Bench(13, "early goku", "Nam"))
        weeklyLineups.add(Bench(14, "end goku", "cyborg tao"))
        weeklyLineups.add(Bench(15, "Nam", "cyborg tao"))
        val seasonBenches = SeasonBenches(fighters, weeklyLineups)
        val objUnderTest = BenchChecker()
        val messages: List<String> = objUnderTest.validateBenches(seasonBenches)
        Assertions.assertTrue(messages.isEmpty())
    }
}