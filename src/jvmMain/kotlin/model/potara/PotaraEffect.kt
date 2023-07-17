package model.potara


data class PotaraEffect(val type: PotaraEffectType,
                        val scale : String, //used when the scale is described "small, medium, large, etc"
                        val intScale : Int = 0 //used when a number is representative of the scale (mostly blue potaras)
 )
