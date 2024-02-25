package com.example.rowdyhacks.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import com.example.rowdyhacks.geocoder.LocationData
import com.example.rowdyhacks.geocoder.LocationRepository

class MapsViewModel(
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val _addressText = MutableLiveData<String>()
    val addressText: LiveData<String>
        get() = _addressText

    private val locationData = addressText.map {
        getLocationData(it)
    }

    val county = locationData.map {
        getCounty(it)
    }

    val city = locationData.map {
        getCity(it)
    }

    fun setAddressText(text: String?) {
        _addressText.value = text ?: "Invalid Data"
    }

    private fun getLocationData(address: String): LocationData {
        return locationRepository.getLocationData(address)
    }
    private fun getCounty(locationData: LocationData): String {
        return locationData.county
    }

    private fun getCity(locationData: LocationData): String {
        return locationData.city
    }
}

class MapsViewModelFactory(
    private val locationRepository: LocationRepository,
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MapsViewModel::class.java)) {
            return MapsViewModel(locationRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}