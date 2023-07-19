package service

import model.MasterList

class LoanTracker {

    fun findRosters(fighterName : String, rosters : List<MasterList>) : List<String>{
        val applicableRosters  = mutableListOf<String>()
        rosters.forEach{roster ->
            if(roster.teamRoster.contains(fighterName)){
                applicableRosters.add(roster.name)
            }
        }
        return applicableRosters
    }
}