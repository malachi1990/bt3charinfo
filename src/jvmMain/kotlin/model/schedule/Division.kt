package model.schedule

import java.util.*

enum class Division(val abbreviatedName: String, vararg val divisionalWeeks: Int) {
        NORTH_KAI("NK", 3, 7, 11),
        EAST_KAI("EK", 4, 8, 12),
        WEST_KAI("WK", 5, 9, 13),
        SOUTH_KAI("SK", 6, 10, 14);

        fun calculateDivisionalWeek(divisionsAlreadyPlayed: Int): Int {
            return divisionalWeeks[divisionsAlreadyPlayed]
        }

        fun isDivisionalWeek(week: Int): Boolean {
            return Arrays.stream(divisionalWeeks).anyMatch { obj: Int ->
                Integer.valueOf(
                    week
                ).equals(obj)
            }
        }

        fun isDivisionalWeekFor(week: Int): Division? {
            for (kai in values()) {
               if (Arrays.stream(kai.divisionalWeeks).anyMatch { obj: Int -> Integer.valueOf( week).equals(obj)}) {
                        return kai
                }
            }
             return null
        }



}