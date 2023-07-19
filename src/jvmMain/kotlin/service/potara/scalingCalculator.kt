package service.potara

fun scalingCalculator ( barsMissing : Int, innateDefense : Int = 0): Int {

 return (barsMissing * 3 + innateDefense).coerceAtMost(20)
}