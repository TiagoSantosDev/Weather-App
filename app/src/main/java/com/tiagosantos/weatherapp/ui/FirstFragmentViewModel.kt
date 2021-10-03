package com.tiagosantos.weatherapp.ui

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.*
import com.tiagosantos.weatherapp.models.CurrentWeather

class FirstFragmentViewModel : ViewModel() {

    companion object {
        private const val PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 100
    }

    var currentWeather = MutableLiveData<CurrentWeather>()
    private operator fun Location.component2() = this.longitude
    private operator fun Location.component1() = this.latitude
    private lateinit var locationManager: LocationManager

    private val LOCATION_PERMISSION_REQ_CODE = 1000
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0

    fun getCurrentLocation(applicationContext: Context, activity: FragmentActivity?) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(applicationContext)
        // checking location permission
        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // request permission
            ActivityCompat.requestPermissions(
                activity as Activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQ_CODE
            )
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                // getting the last known or current location
                println("onSuccess")
                latitude = location.latitude
                longitude = location.longitude
                println("Latitude: $latitude, Longitude: $longitude")
            }
            .addOnFailureListener {
                println("Error!")
            }
    }
    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            LOCATION_PERMISSION_REQ_CODE -> {
                if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    // permission granted
                } else {
                    // permission denied
                    println("No permissions granted")
                }
            }
        }
    }
}


