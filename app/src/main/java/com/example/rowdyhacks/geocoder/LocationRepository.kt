package com.example.rowdyhacks.geocoder

import android.location.Geocoder

class LocationRepository(
    private val geocoder: Geocoder
) {
    fun getLocationData(address: String): LocationData {
        val addresses = geocoder.getFromLocationName(address, 1)
            ?: return LocationData(
                "No City Data",
                "No County Data"
            )

        val addressData = try {
            addresses[0]
        } catch(e: Exception) {
            return LocationData(
                "Invalid Address Format",
                "Format: ADDRESS, CITY, STATE, ZIP"
            )
        }

        return LocationData(
            addressData.locality,
            addressData.subAdminArea
        )
    }
}