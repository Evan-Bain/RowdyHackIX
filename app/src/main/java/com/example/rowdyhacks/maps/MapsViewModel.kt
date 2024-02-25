package com.example.rowdyhacks.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MapsViewModel : ViewModel() {

    private var _addressText = MutableLiveData<String>()
    val addressText: LiveData<String>
        get() = _addressText

    fun setAddressText(text: String?) {
        _addressText.value = text ?: "Invalid Data"
    }
}