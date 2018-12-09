package com.olkunmustafa.sampleweatherapp.data.util.createmodel

import com.olkunmustafa.sampleweatherapp.data.storage.WeatherRequest
import com.olkunmustafa.sampleweatherapp.model.Weather
import io.reactivex.Observable

interface ICreateWeatherModel {

    /**
     * Responsible to convert [String] data in parameter
     * to [com.olkunmustafa.sampleweatherapp.model.Weather] model
     *
     * @since 0.1
     * @author Mustafa Olkun
     */
    fun convertWeatherModel(data: String?): Observable<Weather>

    /**
     * Responsible to create a new [com.olkunmustafa.sampleweatherapp.data.storage.WeatherRequest]
     * model by adding neccessary parameters.
     *
     * @since 0.3
     * @author Mustafa Olkun
     */
    fun createWeatherRequestModel( weather: Weather ) : Observable<WeatherRequest>

}