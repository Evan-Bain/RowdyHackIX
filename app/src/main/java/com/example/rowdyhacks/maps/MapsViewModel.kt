package com.example.rowdyhacks.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.rowdyhacks.cde.CdeRepository
import com.example.rowdyhacks.geocoder.LocationData
import com.example.rowdyhacks.geocoder.LocationRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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

    private val _carSafetyActual = MutableLiveData<Int>()
    val carSafetyActual: LiveData<Int>
        get() = _carSafetyActual

    fun setCarSafetyData(ori: String, offense: String) {
        viewModelScope.launch {
            cdeRepository.getCarSafetyActual(ori, offense)
                .onSuccess {
                    _carSafetyActual.value = it
                }
                .onFailure {
                    _carSafetyActual.value = 0
                }
        }
    }

    private val _possessionSafetyActual = MutableLiveData<Int>()
    val possessionSafetyActual: LiveData<Int>
        get() = _possessionSafetyActual

    fun setPossessionSafetyData(ori: String, offense: List<String>) {
        viewModelScope.launch {
            cdeRepository.getPossessionSafetyActual(ori, offense)
                .onSuccess {
                    _possessionSafetyActual.value = it

                }
                .onFailure {
                    _possessionSafetyActual.value = 0
                }
        }
    }

    private val _personalSafetyActual = MutableLiveData<Int>()
    val personalSafetyActual: LiveData<Int>
        get() = _personalSafetyActual


    fun setPersonalSafetyData(ori: String, offense: List<String>) {
        viewModelScope.launch {
            cdeRepository.getPersonalSafetyActual(ori, offense)
                .onSuccess {
                    _personalSafetyActual.value = it
                }
                .onFailure {
                    _personalSafetyActual.value = 0
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