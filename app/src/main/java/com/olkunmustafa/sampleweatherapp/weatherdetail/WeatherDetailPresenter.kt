package com.olkunmustafa.sampleweatherapp.weatherdetail

import android.annotation.SuppressLint
import android.os.Bundle
import com.olkunmustafa.sampleweatherapp.data.util.createmodel.ICreateWeatherModel
import com.olkunmustafa.sampleweatherapp.data.util.iconutil.IIconUtil
import com.olkunmustafa.sampleweatherapp.data.weatherlist.IWeatherUtil
import com.olkunmustafa.sampleweatherapp.model.Weather
import com.olkunmustafa.sampleweatherapp.weatherdetail.util.ArgumentUtil
import com.olkunmustafa.sampleweatherapp.weatherdetail.util.checkweatherutil.ICheckWeatherUtil
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.IllegalArgumentException
import javax.inject.Inject

class WeatherDetailPresenter @Inject constructor() : IWeatherDetailContract.Presenter {

    private lateinit var view: IWeatherDetailContract.View
    private var dis1: Disposable? = null
    lateinit var weather: Weather

    @Inject
    lateinit var argumentUtil: ArgumentUtil

    @Inject
    lateinit var iweatherUtil: IWeatherUtil

    @Inject
    lateinit var iCreateWeatherModel: ICreateWeatherModel

    lateinit var iCheckWeatherUtil: ICheckWeatherUtil

    override fun setView(view: IWeatherDetailContract.View) {
        this.view = view
    }

    override fun created(args: Bundle?) {

        this.dis1 = this.argumentUtil
            .checkArgsNotNull(args)
            .subscribeOn(Schedulers.io())
            .flatMap { bundle ->
                this.argumentUtil.checkArgRecordContains(bundle)
                    .flatMap { recordID ->
                        this.iweatherUtil.getWeather(recordID)
                    }
                    .flatMap {
                        this.iCreateWeatherModel.convertWeatherModel(it.weatherdata)
                    }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

    }

    override fun destroyed() {
        this.dis1?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

    override fun weatherRequestOnNext(weather: Weather) {
        this.weather = weather

        this.temperatureIcon()
    }

    @SuppressLint("CheckResult")
    override fun temperatureIcon() {
        this.iCheckWeatherUtil.getTemperatureIconUrl(this.weather)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { uri -> view.setTemperatureIcon(uri) },
                { error ->
                    error.printStackTrace()
                    // TODO show an placeholder image if we cannot show icon!
                    // TODO Send event to developers!
                }
            )
    }

    @SuppressLint("CheckResult")
    override fun mainTemperature() {
        this.iCheckWeatherUtil.getCurrentTemperatureText(this.weather)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { view.setCurrentTemperature(it) },
                { error ->
                    error.printStackTrace()
                    // TODO show an error message if we cannot show temperature!
                    // TODO Send event to developers!
                }
            )
    }

}