package com.olkunmustafa.sampleweatherapp.data.weatherlist

import com.olkunmustafa.sampleweatherapp.data.storage.WeatherRequest
import io.reactivex.Observable

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
interface IWeatherListUtil {

    /**
     * Responsible to get weather request list.
     *
     * @since 0.1
     * @author Mustafa Olkun
     */
    fun getWeatherRequestList(): Observable<List<WeatherRequest>>

}