package com.example.rowdyhacks.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.rowdyhacks.cde.CdeRepository
import com.example.rowdyhacks.geocoder.LocationData
import com.example.rowdyhacks.geocoder.LocationRepository
import kotlinx.coroutines.launch

class MapsViewModel(
    private val locationRepository: LocationRepository,
    private val cdeRepository: CdeRepository
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

    private val _carSafetyActual = MutableLiveData<String>()
    val carSafetyActual: LiveData<String>
        get() = _carSafetyActual

    fun setCarSafetyData(ori: String, offense: String) {
        viewModelScope.launch {
            cdeRepository.getCarSafetyActual(ori, offense)
                .onSuccess {
                    _carSafetyActual.value = it.toString()
                }
                .onFailure {
                    _carSafetyActual.value = "Error: $it"
                }
        }
    }

    private val _possessionSafetyActual = MutableLiveData<String>()
    val possessionSafetyActual: LiveData<String>
        get() = _possessionSafetyActual

    fun setPossessionSafetyData(ori: String, offense: List<String>) {
        viewModelScope.launch {
            cdeRepository.getPossessionSafetyActual(ori, offense)
                .onSuccess {
                    _possessionSafetyActual.value = it.toString()
                }
                .onFailure {
                    _possessionSafetyActual.value = "Error: $it"
                }
        }
    }

    private val _personalSafetyActual = MutableLiveData<String>()
    val personalSafetyActual: LiveData<String>
        get() = _personalSafetyActual

    fun setPersonalSafetyData(ori: String, offense: List<String>) {
        viewModelScope.launch {
            cdeRepository.getPersonalSafetyActual(ori, offense)
                .onSuccess {
                    _personalSafetyActual.value = it.toString()
                }
                .onFailure {
                    _personalSafetyActual.value = "Error: $it"
                }
        }
    }
}

class MapsViewModelFactory(
    private val locationRepository: LocationRepository,
    private val cdeRepository: CdeRepository
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MapsViewModel::class.java)) {
            return MapsViewModel(locationRepository, cdeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}