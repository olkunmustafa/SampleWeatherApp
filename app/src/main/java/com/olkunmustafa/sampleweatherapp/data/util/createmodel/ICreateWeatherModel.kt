package com.olkunmustafa.sampleweatherapp.data.util.createmodel

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

}