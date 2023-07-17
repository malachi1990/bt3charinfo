package service.potara

fun scalingCalculator ( barsMissing : Int ): Int {

 return (barsMissing * 3).coerceAtMost(20)
}