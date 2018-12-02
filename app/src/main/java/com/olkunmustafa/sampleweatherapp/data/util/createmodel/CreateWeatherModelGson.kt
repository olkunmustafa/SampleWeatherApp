package com.olkunmustafa.sampleweatherapp.data.util.createmodel

import com.google.gson.Gson
import com.olkunmustafa.sampleweatherapp.model.Weather
import io.reactivex.Observable
import java.lang.NullPointerException

class CreateWeatherModelGson(
    private val gson: Gson?
) : ICreateWeatherModel {

    override fun convertWeatherModel(data: String?): Observable<Weather> {
        if (data == null) {
            return Observable.error<Weather>(NullPointerException("The data should not be null to convert to Weather model."))
        }

        return Observable.just(data)
            .flatMap {
                val weather = this.gson?.fromJson(data, Weather::class.java)
                Observable.just(weather)
            }
    }

}