package com.example.rowdyhacks

object Globals {
    const val CDE_BASE_URL = "https://api.usa.gov/crime/fbi/cde/summarized/agency/"
    const val CDE_API_KEY = "ojq9HUP2GizR0sjwr9u4O7T5WhUfBmYHXZH7HExw"

    data class CarDataNational(
        val motorVehicleTheft: List<Double> = listOf(237.7,230.2,220.8)
    )

    data class PersonalDataNational(
        val aggravatedAssault: List<Double> = listOf(249.2,248.2,250.4),
        val violentCrime: List<Double> = listOf(394.9,383.4,380.8),
        val homicide: List<Double> = listOf(5.3,5.0,5.1),
        val rape: List<Double> = listOf(41.7,44.0,43.6)
    )

    data class PossessionDataNational(
        val burglary: List<Double> = listOf(429.7,378.0,340.5),
        val propertyCrime: List<Double> = listOf(2362.9,2209.8,2130.6),
        val larceny: List<Double> = listOf(1695.5,1601.6,1569.2),
        val robbery: List<Double> = listOf(98.6,86.1,81.8)
    )

    val allBexarOri: List<String> = listOf(

    )

}