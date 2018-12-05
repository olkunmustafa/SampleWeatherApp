package com.olkunmustafa.sampleweatherapp.permissions.location

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.PermissionChecker

class AccessLocation(private var activity: Activity) : IAccessLocationUtil {

    companion object {
        const val PERMISSION_ACCESS_FINE_LOCATION = 191
    }

    override fun hasAccessFineLocationPermission(): Boolean {
        return PermissionChecker.checkCallingOrSelfPermission(
            this.activity, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
                && PermissionChecker.checkCallingOrSelfPermission(
            this.activity, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun getAccessFineLocationPermission() {
        ActivityCompat.requestPermissions(
            this.activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
            PERMISSION_ACCESS_FINE_LOCATION
        )

    }
}