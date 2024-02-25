package com.example.rowdyhacks.maps

import android.annotation.SuppressLint
import android.location.Geocoder
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.rowdyhacks.R
import com.example.rowdyhacks.cde.CdeRepository
import com.example.rowdyhacks.cde.network.CdeApiClient
import com.example.rowdyhacks.databinding.FragmentMapsBinding
import com.example.rowdyhacks.fade
import com.example.rowdyhacks.geocoder.LocationRepository
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers

class MapsFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMapLoadedCallback {

    private lateinit var binding: FragmentMapsBinding
    private lateinit var viewModel: MapsViewModel

    private lateinit var mapView: MapView
    private lateinit var map: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_maps,
            container,
            false
        )

        mapView = binding.mapView

        mapView.apply {
            onCreate(savedInstanceState)
            getMapAsync(this@MapsFragment)
            alpha = 0F
        }

        val geocoder = Geocoder(requireActivity())
        val locationRepository = LocationRepository(geocoder)
        val carSafetyRepository = CdeRepository(
            CdeApiClient.createApi(),
            Dispatchers.Default
        )


        viewModel = ViewModelProvider(this, MapsViewModelFactory(
            locationRepository, carSafetyRepository))[MapsViewModel::class.java]

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.apply {
            this.setOnMapLoadedCallback(this@MapsFragment)
            this.mapType = GoogleMap.MAP_TYPE_NORMAL
        }
    }

    override fun onMapLoaded() {
        //As map loads certain areas display loading indicator at top
        map.setOnCameraMoveListener {
            map.setOnMapLoadedCallback(this@MapsFragment)
        }

        moveToLocation()

        //When map has loaded make map fade in
        binding.mapView.fade(true)
        map.setClickListeners(true)
    }

    /** animate map camera to a certain position **/
    private fun animateCameraMove(position: LatLng) {

        //animate camera position along with the same zoom level
        val cameraPosition = CameraPosition.Builder()
            .target(position)
            .zoom(map.cameraPosition.zoom)
            .build()

        val newCameraPosition = CameraUpdateFactory.newCameraPosition(cameraPosition)

        //disable click listeners during animation
        map.setClickListeners(false)
        map.animateCamera(newCameraPosition, object : GoogleMap.CancelableCallback {
            override fun onCancel() {
                map.setClickListeners(true)
            }

            override fun onFinish() {
                map.setClickListeners(true)
            }

        })
    }

    @SuppressLint("MissingPermission")
    /** moves to current location if enabled **/
    private fun moveToLocation() {
        animateCameraMove(LatLng(
            31.0, -100.0
           )
        )

    }

    /** adds or removes all click listeners used in GoogleMap **/
    private fun GoogleMap.setClickListeners(setListeners: Boolean) {

        if(setListeners) {
            //move to marker position and show the marker title
            this.setOnMarkerClickListener {
                animateCameraMove(it.position)

                it.showInfoWindow()
                true
            }
        } else {
            //info click listener not disabled as it can only be activated by marker listener
            this.setOnMapClickListener(null)
            this.setOnMarkerClickListener(null)
        }

    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_view, menu)
        val searchItem: MenuItem = menu.findItem(R.id.search_menu_item)
        val searchView: SearchView = searchItem.actionView as SearchView
        searchView.queryHint = "Search addresses"

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null) {
                    viewModel.setAddressText(query)
                    viewModel.setCarSafetyData("TX0070100", "motor-vehicle-theft")
                    viewModel.setPersonalSafetyData(
                        "TX0070100",
                        listOf(
                            "aggravated-assault",
                            "violent-crime",
                            "homicide",
                            "rape"
                        )
                    )
                    viewModel.setPossessionSafetyData(
                        "TX0070100",
                        listOf(
                            "burglary",
                            "property-crime",
                            "larceny",
                            "robbery"
                        )
                    )
                }
                return false
            }

            //adding filter to SearchView
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        super.onCreateOptionsMenu(menu, inflater)
    }
}