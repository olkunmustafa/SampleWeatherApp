package com.olkunmustafa.sampleweatherapp.weathermain

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.location.Location
import com.olkunmustafa.sampleweatherapp.data.apiclient.ApiClient
import com.olkunmustafa.sampleweatherapp.data.services.IGetCurrentWeatherMap
import com.olkunmustafa.sampleweatherapp.permissions.location.AccessLocation
import com.olkunmustafa.sampleweatherapp.permissions.location.IAccessLocationUtil
import com.olkunmustafa.sampleweatherapp.util.Optional
import com.olkunmustafa.sampleweatherapp.util.location.IKLocationSettingsResult
import com.olkunmustafa.sampleweatherapp.util.location.KLocationSettingsHelper
import com.olkunmustafa.sampleweatherapp.util.location.LocationServiceUtil
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
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

    @Inject
    lateinit var kLocationSettingsHelper: KLocationSettingsHelper

    override fun setView(view: IWeatherContract.View) {
        this.view = view
    }

    override fun created() {
        this.view.replaceFragment()
    }

    @SuppressLint("CheckResult")
    override fun saveButtonClicked() {
        this.locationRequest()
//        val iGetCurrentWeatherMap =ApiClient.createService(IGetCurrentWeatherMap::class.java)
//        iGetCurrentWeatherMap
//            .getCurrentWeather(41.00, 29.00, "d0fcc375ac746c5d71388a395a4e3539")
//            .subscribeOn(Schedulers.io())
//            .subscribe { it -> println( "TEST_BASE" + it.main.temp )}


    }

    override fun locationRequest() {
        if (this.iAccessLocationUtil.hasAccessFineLocationPermission()) {

//            this.kLocationSettingsHelper
//                .createDefaultLocationRequest()
//                .flatMap { loc ->
//                    val iGetCurrentWeatherMap = ApiClient.createService(IGetCurrentWeatherMap::class.java)
//                    iGetCurrentWeatherMap
//                        .getCurrentWeather(loc.latitude, loc.longitude, "d0fcc375ac746c5d71388a395a4e3539")
//                        .subscribeOn(Schedulers.io())
//                        .map { it -> println( "TEST_BASE" + it.main.temp )}
//
//                }
//                .subscribe()

        } else {
            this.iAccessLocationUtil.getAccessFineLocationPermission()

        }
    }

    override fun activityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                AccessLocation.PERMISSION_ACCESS_FINE_LOCATION -> this.locationRequest()

            }
        }

    }

}