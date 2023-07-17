package service.potara

import model.potara.Potara
import model.potara.PotaraEffectType

fun calculateBlueModifier(stat : PotaraEffectType, build : List<Potara>) : Int {
    var totalModifier = 0

    build.forEach { potara ->
        potara.effects
            .filter { stat == it.type }
            .forEach {
                println("found match")
                totalModifier += it.intScale
            }
    }
    return totalModifier.coerceAtMost(20)
}
