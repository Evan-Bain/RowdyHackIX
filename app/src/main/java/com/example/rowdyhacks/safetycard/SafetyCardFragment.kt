package com.example.rowdyhacks.safetycard

import android.location.Geocoder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.rowdyhacks.Globals.allBexarOri
import com.example.rowdyhacks.R
import com.example.rowdyhacks.cde.CdeRepository
import com.example.rowdyhacks.cde.network.CdeApiClient
import com.example.rowdyhacks.databinding.FragmentSafetyCardBinding
import com.example.rowdyhacks.geocoder.LocationRepository
import com.example.rowdyhacks.maps.MapsViewModel
import com.example.rowdyhacks.maps.MapsViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class SafetyCardFragment : Fragment() {

    private lateinit var binding: FragmentSafetyCardBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_safety_card,
            container,
            false
        )

        val safetyArray = arguments?.getIntArray("safety_array")
        val city = arguments?.getString("city")
        val county = arguments?.getString("county")

        binding.countyText.text = county.toString()
        binding.textViewCarSafetyBar.text = safetyArray?.get(0).toString()
        binding.textViewPersonalSafetyBar.text = safetyArray?.get(1).toString()
        binding.textViewPosessionSafetyBar.text = safetyArray?.get(2).toString()

        // Inflate the layout for this fragment
        return binding.root
    }

}