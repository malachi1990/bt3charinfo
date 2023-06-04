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

    val firstB1 : B1Move,
    val secondB1 : B1Move,

    val firstB2 : B2Move,
    val secondB2 : B2Move,
    val ultimate : B2Move


){
}