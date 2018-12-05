package com.olkunmustafa.sampleweatherapp.weatherdetail.util.checkweatherutil

import com.olkunmustafa.sampleweatherapp.data.util.iconutil.IIconUtil
import com.olkunmustafa.sampleweatherapp.data.util.temperatureutil.ITemperatureUtil
import com.olkunmustafa.sampleweatherapp.model.Weather
import io.reactivex.Observable

class CheckWeatherObject(
    private var iIconUtil: IIconUtil,
    private val iTemperatureUtil: ITemperatureUtil
) : ICheckWeatherUtil {

    override fun getTemperatureIconUrl(weather: Weather): Observable<String> {
        return Observable.just(weather)
            .map {
                if (it.weather == null || it.weather.size == 0 || it.weather[0].icon.isEmpty()) {
                    throw IllegalArgumentException( "To show wether icon on detail page, weather object should arrang correctly!" )
                }

                this.iIconUtil.getIconFullUrl(it.weather[0].icon)
            }
    }

    override fun getCurrentTemperatureText(weather: Weather): Observable<String> {
        return Observable.just(weather)
            .map {
                if( it.main == null ){
                    throw java.lang.IllegalArgumentException( "To show suitable weather temperature, Main object shpuld not be null" )
                }

                this.iTemperatureUtil.getStyledTemperature(it.main.temp)
            }
    }
}