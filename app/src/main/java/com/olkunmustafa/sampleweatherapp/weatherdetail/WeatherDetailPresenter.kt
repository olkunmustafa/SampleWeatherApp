package com.olkunmustafa.sampleweatherapp.weatherdetail

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.Menu
import com.olkunmustafa.sampleweatherapp.R
import com.olkunmustafa.sampleweatherapp.data.storage.WeatherRequest
import com.olkunmustafa.sampleweatherapp.data.util.createmodel.ICreateWeatherModel
import com.olkunmustafa.sampleweatherapp.data.util.dateutil.IDateUtil
import com.olkunmustafa.sampleweatherapp.data.weatherlist.IWeatherUtil
import com.olkunmustafa.sampleweatherapp.model.Weather
import com.olkunmustafa.sampleweatherapp.weatherdetail.util.ArgumentUtil
import com.olkunmustafa.sampleweatherapp.weatherdetail.util.checkweatherutil.ICheckWeatherUtil
import com.olkunmustafa.sampleweatherapp.weathermain.listener.IFragmentListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherDetailPresenter @Inject constructor() : IWeatherDetailContract.Presenter {

    private lateinit var view: IWeatherDetailContract.View
    private var dis1: Disposable? = null
    private var iFragmentListener: IFragmentListener? = null

    lateinit var weather: Weather
    lateinit var weatherRequest: WeatherRequest

    @Inject
    lateinit var argumentUtil: ArgumentUtil

    @Inject
    lateinit var iweatherUtil: IWeatherUtil

    @Inject
    lateinit var iCreateWeatherModel: ICreateWeatherModel

    @Inject
    lateinit var iCheckWeatherUtil: ICheckWeatherUtil

    @Inject
    lateinit var iDateUtil: IDateUtil

    override fun setView(view: IWeatherDetailContract.View) {
        this.view = view
    }

    override fun attached(context: Context?) {
        if (context is IFragmentListener) {
            iFragmentListener = context
        }
    }

    @SuppressLint("CheckResult")
    override fun created(args: Bundle?) {
        this.argumentUtil
            .checkArgsNotNull(args)
            .subscribeOn(Schedulers.io())
            .flatMap { bundle ->
                this.argumentUtil.checkArgRecordContains(bundle)
            }
            .subscribe(
                { recordID ->
                    createDetailInterface(recordID)
                },
                {
                    it.printStackTrace()
                }
            )
    }

    override fun createDetailInterface(recordID: Int) {

        this.dis1 = this.iweatherUtil.getWeather(recordID)
            .subscribeOn(Schedulers.io())
            .doOnNext {
                weatherRequest = it
            }
            .flatMap {
                this.iCreateWeatherModel.convertWeatherModel(it.weatherdata)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { it -> weatherRequestOnNext(it) },
                { err -> err.printStackTrace() }
            )

    }

    override fun destroyed() {
        this.dis1?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

    override fun preparedOptionsMenu(menu: Menu?) {
        val item = menu?.findItem(R.id.send_new_request)
        item?.isVisible = false
    }

    override fun weatherRequestOnNext(weather: Weather) {
        this.weather = weather

        this.temperatureIcon()
        this.mainTemperature()
        this.minMaxTemperature()
        this.location()
        this.requestTime()
        this.description()
        this.wind()
        this.humidity()
        this.sunTimes()
        this.visibility()
        this.view.showAllViews(300)
    }

    @SuppressLint("CheckResult")
    override fun temperatureIcon() {
        this.iCheckWeatherUtil.getTemperatureIconUrl(this.weather)
            .subscribeOn(Schedulers.io())
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
            .subscribeOn(Schedulers.io())
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

    @SuppressLint("CheckResult")
    override fun minMaxTemperature() {
        this.iCheckWeatherUtil.getMinMaxTemperatureText(this.weather)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { view.setCurrentMinMax(it) },
                { error ->
                    error.printStackTrace()
                    // TODO show an error message if we cannot show temperature!
                    // TODO Send event to developers!
                }
            )
    }

    @SuppressLint("CheckResult")
    override fun location() {
        this.iCheckWeatherUtil.getLocationName(weather)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { view.setLocation(it) },
                { error ->
                    error.printStackTrace()
                    // TODO show an error message if we cannot show location!
                    // TODO Send event to developers!
                }
            )
    }

    override fun requestTime() {
        val requestTime = this.iDateUtil.formatDate(this.weatherRequest.requestTime)
        this.view.setRequestTime(requestTime)
    }

    @SuppressLint("CheckResult")
    override fun description() {
        this.iCheckWeatherUtil
            .getDesciption(weather)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.setDescription(it)
                },
                { error ->
                    error.printStackTrace()
                    // TODO show an error message if we cannot show Description!
                    // TODO Send event to developers!
                }
            )
    }

    @SuppressLint("CheckResult")
    override fun wind() {
        this.iCheckWeatherUtil.getWindModel(this.weather)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.setWindSpeed(it.speed.toString())
                    view.setWindDegree(it.deg.toString())
                },
                { error ->
                    error.printStackTrace()
                    // TODO show an error message if we cannot show wind!
                    // TODO Send event to developers!
                }
            )
    }

    @SuppressLint("CheckResult")
    override fun humidity() {
        this.iCheckWeatherUtil.getHumidityValue(this.weather)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.setHumidity(it)
                },
                { error ->
                    error.printStackTrace()
                    // TODO show an error message if we cannot show Humidity!
                    // TODO Send event to developers!
                }
            )
    }

    @SuppressLint("CheckResult")
    override fun sunTimes() {
        this.iCheckWeatherUtil
            .getSunTimes(this.weather)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val sunrise = this.iDateUtil.formatTimeFromSecond(it.sunrise)
                    val sunset = this.iDateUtil.formatTimeFromSecond(it.sunset)

                    view.setSunrise(sunrise)
                    view.setSunset(sunset)
                },
                { error ->
                    error.printStackTrace()
                    // TODO show an error message if we cannot show Sunrise or Sunset!
                    // TODO Send event to developers!
                }
            )

    }

    override fun visibility() {
        view.setVisibility(weather.visibility.toString())
    }
}