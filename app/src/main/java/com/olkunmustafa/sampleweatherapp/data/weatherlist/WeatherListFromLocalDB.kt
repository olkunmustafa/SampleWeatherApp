package com.olkunmustafa.sampleweatherapp.data.weatherlist

import com.olkunmustafa.sampleweatherapp.data.storage.WeatherDatabase
import com.olkunmustafa.sampleweatherapp.data.storage.WeatherRequest
import com.olkunmustafa.sampleweatherapp.data.storage.WeatherRequestDao
import io.reactivex.Observable

class WeatherListFromLocalDB(weatherDatabase: WeatherDatabase) : IWeatherListUtil {

    private var mWeatherRequestDao: WeatherRequestDao = weatherDatabase.weatherModel()

    override fun getWeatherRequestList(): Observable<List<WeatherRequest>> {
        return Observable.fromCallable {
            this.mWeatherRequestDao.getAll()
        }
    }

}