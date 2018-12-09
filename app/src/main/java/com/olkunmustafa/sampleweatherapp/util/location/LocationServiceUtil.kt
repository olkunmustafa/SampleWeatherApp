package com.olkunmustafa.sampleweatherapp.util.location

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.location.Location
import android.location.LocationManager
import com.google.android.gms.location.LocationServices

class LocationServiceUtil(private var activity: Activity) {

    @SuppressLint("MissingPermission")
    fun getLastLocation(): Location? {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)

        fusedLocationClient.lastLocation
            .addOnSuccessListener {location: Location? ->
                location?.latitude
                return@addOnSuccessListener
            }

        return null
    }

}