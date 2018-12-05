package com.olkunmustafa.sampleweatherapp.weathermain

import android.app.Activity
import android.content.Intent
import com.olkunmustafa.sampleweatherapp.data.apiclient.ApiClient
import com.olkunmustafa.sampleweatherapp.data.services.IGetCurrentWeatherMap
import com.olkunmustafa.sampleweatherapp.permissions.location.AccessLocation
import com.olkunmustafa.sampleweatherapp.permissions.location.IAccessLocationUtil
import com.olkunmustafa.sampleweatherapp.util.Optional
import com.olkunmustafa.sampleweatherapp.util.location.LocationServiceUtil
import io.reactivex.Observable
import javax.inject.Inject

class WeatherPresenter @Inject constructor() : IWeatherContract.Presenter {

    private lateinit var view: IWeatherContract.View
    private val appid = "d0fcc375ac746c5d71388a395a4e3539"

    @Inject
    lateinit var apiClient: ApiClient

    @Inject
    lateinit var iAccessLocationUtil: IAccessLocationUtil

    @Inject
    lateinit var locationServiceUtil: LocationServiceUtil

    override fun setView(view: IWeatherContract.View) {
        this.view = view
    }

    override fun created() {
        this.view.replaceFragment()
    }

    override fun saveButtonClicked() {
        this.searchDeviceLocation()
    }

    override fun searchDeviceLocation() {
        if (this.iAccessLocationUtil.hasAccessFineLocationPermission()) {
            Observable.just(Optional(locationServiceUtil.getLastLocation()))
                .map {
                    if( it.isEmpty ){
                        throw NullPointerException( "Last location can not found!" )
                    }

                    it.get()
                }
                .map { loc ->
                    val iGetCurrentWeatherMap = apiClient.createService(IGetCurrentWeatherMap::class.java)
                    iGetCurrentWeatherMap
                        .getCurrentWeather( loc.latitude, loc.longitude, appid )
                        .map {w ->
                            println( "BASIS" + w.weather[0].description )
                        }
                }
                .subscribe()
        } else {
            this.iAccessLocationUtil.getAccessFineLocationPermission()

        }
    }

    override fun activityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                AccessLocation.PERMISSION_ACCESS_FINE_LOCATION -> this.searchDeviceLocation()

            }
        }

    }

}