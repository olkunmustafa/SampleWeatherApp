package com.olkunmustafa.sampleweatherapp.weatherdetail.util.checkweatherutil

import com.olkunmustafa.sampleweatherapp.model.Weather
import io.reactivex.Observable

interface ICheckWeatherUtil {

    /**
     * @since 0.2
     * @author Mustafa Olkun
     * @return an [IllegalArgumentException] or [String] after [com.olkunmustafa.sampleweatherapp.model.Weather_] objects
     */
    fun getTemperatureIconUrl(weather: Weather): Observable<String>

    /**
     * @since 0.2
     * @author Mustafa Olkun
     * @return [IllegalArgumentException] or  Suitable [String] to show the temperature.
     */
    fun getCurrentTemperatureText(weather: Weather): Observable<String>

}