package com.olkunmustafa.sampleweatherapp.data.weatherlist

import com.olkunmustafa.sampleweatherapp.data.storage.WeatherRequest
import com.olkunmustafa.sampleweatherapp.model.Weather
import io.reactivex.Observable
import java.sql.Date

/**
 * Methods that this class contains
 * reponsible to get and manipulate weather lists
 * from remote or local database.
 *
 * The remote server can be Firebase Storage,
 * or any phsyical server. This interface not care about
 * where we are calling the results from.
 *
 * @since 0.1
 * @author Mustafa Olkun
 */
interface IWeatherUtil {

    /**
     * Responsible to get weather request list.
     *
     * @since 0.1
     * @author Mustafa Olkun
     */
    fun getWeatherRequestList(): Observable<List<WeatherRequest>>

    /**
     * Calls a spesific weather
     *
     * @since 0.2
     * @author Mustafa Olkun
     */
    fun getWeather(id: Int): Observable<WeatherRequest>

    /**
     * Saves weather request in Local database.
     *
     * @since 0.2
     * @author Mustafa Olkun
     */
    fun saveWeatherRequest(weatherRequest: WeatherRequest) : Observable<Long>
}