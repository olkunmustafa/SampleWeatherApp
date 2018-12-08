package com.olkunmustafa.sampleweatherapp.weathermain

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import com.olkunmustafa.sampleweatherapp.data.apiclient.ApiClient
import com.olkunmustafa.sampleweatherapp.data.services.IGetCurrentWeatherMap
import com.olkunmustafa.sampleweatherapp.data.util.createmodel.ICreateWeatherModel
import com.olkunmustafa.sampleweatherapp.data.weatherlist.IWeatherUtil
import com.olkunmustafa.sampleweatherapp.permissions.location.AccessLocation
import com.olkunmustafa.sampleweatherapp.permissions.location.IAccessLocationUtil
import com.olkunmustafa.sampleweatherapp.util.location.KLocationSettingsHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import org.greenrobot.eventbus.EventBus

class WeatherPresenter @Inject constructor() : IWeatherContract.Presenter {

    private lateinit var view: IWeatherContract.View
    private val appid = "d0fcc375ac746c5d71388a395a4e3539"

    @Inject
    lateinit var apiClient: ApiClient

    @Inject
    lateinit var iAccessLocationUtil: IAccessLocationUtil

    @Inject
    lateinit var kLocationSettingsHelper: KLocationSettingsHelper

    @Inject
    lateinit var iWeatherUtil: IWeatherUtil

    @Inject
    lateinit var iCreateWeatherModel: ICreateWeatherModel

    override fun setView(view: IWeatherContract.View) {
        this.view = view
    }

    override fun created() {
        this.view.replaceFragment()
    }

    override fun saveButtonClicked() {
        this.locationRequest()

    }

    @SuppressLint("CheckResult")
    override fun locationRequest() {
        if (this.iAccessLocationUtil.hasAccessFineLocationPermission()) {

            this.kLocationSettingsHelper
                .createDefaultLocationRequest()
                .toObservable()
                .flatMap { loc ->
                    val iGetCurrentWeatherMap = apiClient.createService(IGetCurrentWeatherMap::class.java)

                    iGetCurrentWeatherMap
                        .getCurrentWeather(loc.latitude, loc.longitude, appid)
                        .subscribeOn(Schedulers.io())
                }
                .flatMap {
                    iCreateWeatherModel.createWeatherRequestModel(it)
                }
                .flatMap { request ->
                    this.iWeatherUtil.saveWeatherRequest(request)
                        .map {
                            request.uid = it.toInt()
                            request
                        }

                }
                .observeOn( AndroidSchedulers.mainThread() )
                .subscribe(
                    { request ->
                        EventBus.getDefault().post(request)
                    },
                    { err ->
                        err.printStackTrace()
                    },
                    {
                        view.hideLoading()
                    },
                    {
                        view.showLoading()
                    }

                )

        } else {
            this.iAccessLocationUtil.getAccessFineLocationPermission()

        }
    }


    override fun requestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
            when (requestCode) {

                AccessLocation.PERMISSION_ACCESS_FINE_LOCATION -> this.locationRequest()

            }
    }

}