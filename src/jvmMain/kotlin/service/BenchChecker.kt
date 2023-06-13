package service

import model.benching.Bench
import model.benching.BenchLineup
import model.benching.SeasonBenches
import java.util.*
import java.util.function.Predicate


class BenchChecker {


    fun validateBenches(benches: SeasonBenches): List<String> {
        val messages: MutableSet<String> = HashSet()
        val lineups = buildLineups(benches)
        messages.addAll(checkFor12WeekRule(benches, lineups)!!)
        messages.addAll(checkWeeklyLineupChanges(lineups)!!)
        messages.addAll(checkWeeklyBenchCount(benches)!!)
        return messages.toMutableList()
    }

    private fun buildLineups(benches: SeasonBenches): List<BenchLineup> {
        val lineups: MutableList<BenchLineup> = ArrayList()
        for (bench in benches.benches) {
            val fighterNames: MutableList<String> = ArrayList(benches.fighters)
            fighterNames.remove(bench.weeklyBench)
            fighterNames.remove(bench.faBench)
            val lineup = BenchLineup(bench.week, fighterNames, bench.weeklyBench, bench.faBench)

            lineups.add(lineup)
        }
        return lineups
    }

    private fun checkFor12WeekRule(benches: SeasonBenches, lineups: List<BenchLineup>): List<String>? {
        val messages: MutableList<String> = ArrayList()
        for (fighter in benches.fighters) {
            var count = 0
            for (lineup in lineups) {
                if (lineup.activeFighters.contains(fighter)) {
                    count++
                }
            }
            if (count > 12) {
                messages.add("$fighter is in more than 12 weeks.")
            }
        }
        return messages
    }

    private fun checkWeeklyLineupChanges(lineups: List<BenchLineup>): List<String> {
        val messages: MutableList<String> = ArrayList()
        val lineupMapByWeek: Map<Int, BenchLineup> = lineups.associateBy { bench -> bench.week }
        for ((week, currentLineup) in lineupMapByWeek) {
            val followingLineup = lineupMapByWeek[week + 1]
            if (followingLineup != null) {
                if (currentLineup.activeFighters.containsAll(followingLineup.activeFighters) &&
                    followingLineup.activeFighters.containsAll(currentLineup.activeFighters)
                ) {
                    messages.add("week " + week + " and " + (week + 1) + " have the same lineup. Back to back identical lineups are not allowed")
                }
            }
        }
        return messages
    }

    private fun checkWeeklyBenchCount(benches: SeasonBenches): List<String>? {
        val messages: MutableList<String> = ArrayList()
        val fa: String = benches.fighters.get(benches.fighters.size - 1)
        val weeklyBenches: List<String?> = benches.benches.stream().map(Bench::weeklyBench).toList()
        //raw check
        for (fighter in benches.fighters) {
            if (fighter == fa) {
                val faBenches: List<Bench> = benches.benches.stream().filter { bench ->
                    bench.weeklyBench.equals(fighter)
                }.toList()
                for (bench in faBenches) {
                    //get the number of regular weekly benches
                    val benchCount = Collections.frequency(weeklyBenches, bench.faBench)
                    //trying to count the weeks where the main fighter was benched for the FA
                    val benchPredicate: Predicate<Bench> = Predicate<Bench> { bench1 ->
                        bench1.weeklyBench == fighter &&
                                bench1.faBench == bench.faBench
                    }
                    val weeklyBenchForMainRosterCharacter: Long =
                        benches.benches.stream().filter(benchPredicate).count()
                    if (benchCount + weeklyBenchForMainRosterCharacter > 3) {
                        messages.add(bench.faBench + " has more than 3 benches")
                    }
                }
            } else {
                if (Collections.frequency(weeklyBenches, fighter) > 3) {
                    messages.add("$fighter has more than 3 benches")
                }
            }
        }
        return messages
    }
}