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

    val allBexarOri: List<String> = listOf("TX0150000", "TX0150100", "TX0150200", "TX0150300", "TX0150400", "TX0150500",
        "TX0150700", "TX0150800", "TX0150900", "TX0151000", "TX0151100", "TX0151200",
        "TX0151300", "TX0151400", "TX0151500", "TX0151600", "TX0151700", "TX0151900",
        "TX0152000", "TX0152100", "TX0152300", "TX0153000", "TX015319E", "TX0153200",
        "TX015349E", "TX0153500", "TX0153600", "TX0153800", "TX0157000")



}