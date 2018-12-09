package com.olkunmustafa.sampleweatherapp.data.services

import com.olkunmustafa.sampleweatherapp.model.Weather
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface IGetCurrentWeatherMap {

    @GET("weather?units=metric")
    fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String
    ): Observable<Weather>

}