package com.olkunmustafa.sampleweatherapp.permissions.location

interface IAccessLocationUtil {

    fun hasAccessFineLocationPermission(): Boolean

    fun getAccessFineLocationPermission(): Unit

}