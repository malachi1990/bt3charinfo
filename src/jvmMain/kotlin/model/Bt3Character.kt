package model

data class Bt3Character (
    val name : String,
    val baseHP : Int,
    val bashRushMeleeDamage : Int,
    val baseSmashMeleeDamage : Int,
    val baseRushBlastDamage : Int,
    val rushBlastVolleyCount : Int,
    val baseVolleyDamage : Int,
    val baseChargedBlastDamage : Int,
    val baseThrowDamage : Int,
    val baseActiveCharge : Int,
    val basePassiveCharge : Int,
    val baseUnderwaterActiveCharge : Int,

    val baseKiPower : Int,
    val innateDefense : Float,
    val baseB1charge : Int,
    val maxblastStocks : Int,
    val baseSwitchGaugeCharge : Int,

    val baseMpmDuration : Int,
    val baseEvilness : Int,
    val baseVanishingAttackCount : Int,
    val baseDragonDashCount : Int,

    val firstB2Name : String,
    val firstB2Description : String,
    val firstB2Damage : Int,
    val firstB2BoostDamage : Int,

    val secondB2Name : String,
    val secondB2Description : String,
    val secondB2Damage : Int,
    val secondB2BoostDamage : Int,

    val ultimateName : String,
    val ultimateDescription : String,
    val ultimateDamage : Int,
    val ultimateBoostDamage : Int,

){
}