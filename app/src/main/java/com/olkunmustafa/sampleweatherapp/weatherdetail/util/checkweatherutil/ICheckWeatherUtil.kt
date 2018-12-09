package com.olkunmustafa.sampleweatherapp.weatherdetail.util.checkweatherutil

import com.olkunmustafa.sampleweatherapp.model.Sys
import com.olkunmustafa.sampleweatherapp.model.Weather
import com.olkunmustafa.sampleweatherapp.model.Wind
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

    /**
     * @since 0.2
     * @author Mustafa Olkun
     * @return [IllegalArgumentException] or Suitable [String] to show the min and max.
     */
    fun getMinMaxTemperatureText(weather: Weather) : Observable<String>

    /**
     * @since 0.2
     * @author Mustafa Olkun
     * @return [IllegalArgumentException] or Suitable [String] to show location name.
     */
    fun getLocationName(weather: Weather) : Observable<String>

    /**
     * @since 0.2
     * @author Mustafa Olkun
     * @return [IllegalArgumentException] or [Wind] to show speed and degree
     */
    fun getWindModel(weather: Weather) : Observable<Wind>

    /**
     * @since 0.2
     * @author Mustafa Olkun
     * @return [IllegalArgumentException] or Suitable [String] to show humdiity.
     */
    fun getHumidityValue(weather: Weather) : Observable<String>

    /**
     * @since 0.2
     * @author Mustafa Olkun
     * @return [IllegalArgumentException] or Suitable [String] to show Sunrise time.
     */
    fun getSunTimes(weather: Weather) : Observable<Sys>

    /**
     *
     *
     * @since 0.2
     * @author Mustafa Olkun
     * @return [IllegalArgumentException], [NullPointerException] or suitable [String] to show description.
     */
    fun getDesciption( weather : Weather ) : Observable<String>
}