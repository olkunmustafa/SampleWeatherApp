package com.olkunmustafa.sampleweatherapp.data.weatherlist

import com.olkunmustafa.sampleweatherapp.data.storage.WeatherRequest
import com.olkunmustafa.sampleweatherapp.data.storage.WeatherRequestDao
import io.reactivex.Observable

class WeatherListFromLocalDB : IWeatherListUtil {

    lateinit var mWeatherRequestDao: WeatherRequestDao

    override fun getWeatherRequestList(): Observable<List<WeatherRequest>> {
        return Observable.fromCallable {
            this.mWeatherRequestDao.getAll()
        }
    }

}