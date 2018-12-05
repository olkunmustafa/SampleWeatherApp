package com.olkunmustafa.sampleweatherapp.weatherdetail.util.checkweatherutil

import com.olkunmustafa.sampleweatherapp.data.util.dateutil.IDateUtil
import com.olkunmustafa.sampleweatherapp.data.util.iconutil.IIconUtil
import com.olkunmustafa.sampleweatherapp.data.util.temperatureutil.ITemperatureUtil
import com.olkunmustafa.sampleweatherapp.model.Sys
import com.olkunmustafa.sampleweatherapp.model.Weather
import com.olkunmustafa.sampleweatherapp.model.Wind
import io.reactivex.Observable
import kotlin.NullPointerException

class CheckWeatherObject(
    private var iIconUtil: IIconUtil,
    private val iTemperatureUtil: ITemperatureUtil
) : ICheckWeatherUtil {

    override fun getTemperatureIconUrl(weather: Weather): Observable<String> {
        return Observable.just(weather)
            .map {
                if (it.weather == null || it.weather.size == 0 || it.weather[0].icon.isEmpty()) {
                    throw IllegalArgumentException("To show wether icon on detail page, weather object should arrang correctly!")
                }

                this.iIconUtil.getIconFullUrl(it.weather[0].icon)
            }
    }

    override fun getCurrentTemperatureText(weather: Weather): Observable<String> {
        return Observable.just(weather)
            .map {
                if (it.main == null) {
                    throw java.lang.IllegalArgumentException("To show suitable weather temperature, Main object shpuld not be null")
                }

                this.iTemperatureUtil.getStyledTemperature(it.main.temp)
            }
    }

    override fun getMinMaxTemperatureText(weather: Weather): Observable<String> {
        return Observable.just(weather)
            .map {
                if (it.main == null) {
                    throw java.lang.IllegalArgumentException("To show suitable min and max temperature, Main object shpuld not be null")
                }

                this.iTemperatureUtil.getStyledMinMaxTemperature(it.main.tempMin, it.main.tempMax)
            }
    }

    override fun getLocationName(weather: Weather): Observable<String> {
        return Observable.just(weather)
            .map {
                if (it.name == null) {
                    throw NullPointerException("To show suitable location, name object should not be null in weather")
                }

                it.name
            }
    }

    override fun getWindModel(weather: Weather): Observable<Wind> {
        return Observable.just(weather)
            .map {
                if (it.wind == null) {
                    throw NullPointerException("To show suitable Wind object, the wind should not be null")
                }

                it.wind
            }
    }

    override fun getHumidityValue(weather: Weather): Observable<String> {
        return Observable.just(weather)
            .map {
                if (it.main == null) {
                    throw NullPointerException("To show suitable humidity, Main object shpuld not be null")
                }

                it.main.humidity.toString()
            }
    }

    override fun getSunTimes(weather: Weather): Observable<Sys> {
        return Observable.just(weather)
            .flatMap {
                if (it.sys == null) {
                    throw NullPointerException("To show suitable sunTimes time, Main object shpuld not be null")
                }

                if (it.sys.sunrise <= 0 || it.sys.sunset <= 0) {
                    throw IllegalArgumentException("Sunrise and Sunset time can not be smaller/equals to zero")
                }

                Observable.just( it.sys )
            }
    }

    override fun getDesciption(weather: Weather): Observable<String> {
        return Observable.just(weather)
            .map {
                if (it.weather == null || it.weather.size == 0 || it.weather[0].description == null ||  it.weather[0].description.isEmpty()) {
                    throw IllegalArgumentException("To show wether icon on detail page, weather object should arrang correctly!")
                }

                it.weather[0].description
            }
    }
}