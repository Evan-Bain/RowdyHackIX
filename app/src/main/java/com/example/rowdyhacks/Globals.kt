package com.example.rowdyhacks

object Globals {
    const val CDE_BASE_URL = "https://api.usa.gov/crime/fbi/cde/summarized/agency/"
    const val CDE_API_KEY = "ojq9HUP2GizR0sjwr9u4O7T5WhUfBmYHXZH7HExw"

    data class CarDataNational(
        val motorVehicleTheft: Int
    )

    data class PersonalDataNational(
        val aggravatedAssault: Int,
        val violentCrime: Int,
        val homicide: Int,
        val rape: Int
    )

    data class PossessionDataNational(
        val burglary: Int,
        val propertyCrime: Int,
        val larceny: Int,
        val robbery: Int
    )

}