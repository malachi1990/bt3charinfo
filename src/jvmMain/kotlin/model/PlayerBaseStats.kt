package model

data class PlayerBaseStats ( val name : String,
                             val baseHP : Int,
                             val defenseLevel : Float,

                             val baseRushMeleeDamage : Int,
                             val baseSmashMeleeDamage : Int,

                             val baseRushBlastDamage : Int,
                             val rushBlastVolleyCount : Int,
                             val baseVolleyDamage : Int,

                             val baseChargedBlastDamage : Int,
                             val baseThrowDamage : Int,

                             val baseActiveCharge : Int,
                             val basePassiveCharge : Int,

                             val baseKiPower : Int,
                             val baseB1charge : Int,
                             val maxblastStocks : Int,
                             val baseSwitchGaugeCharge : Int,

                             val baseEvilness : Int,
                             val baseMpmDuration : Int,
                             val baseVanishingAttackCount : Int,
                             val baseDragonDashCount : Int,
                             val baseUnderwaterActiveCharge : Int
){
}