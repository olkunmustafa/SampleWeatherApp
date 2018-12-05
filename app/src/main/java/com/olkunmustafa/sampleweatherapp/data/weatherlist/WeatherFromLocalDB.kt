package com.olkunmustafa.sampleweatherapp.data.weatherlist

import com.olkunmustafa.sampleweatherapp.data.storage.WeatherDatabase
import com.olkunmustafa.sampleweatherapp.data.storage.WeatherRequest
import com.olkunmustafa.sampleweatherapp.data.storage.WeatherRequestDao
import io.reactivex.Observable

class WeatherFromLocalDB(weatherDatabase: WeatherDatabase) : IWeatherUtil {

    private var mWeatherRequestDao: WeatherRequestDao = weatherDatabase.weatherModel()

    override fun getWeatherRequestList(): Observable<List<WeatherRequest>> {
        return Observable.fromCallable {
            this.mWeatherRequestDao.getAll()
        }
    }

    override fun getWeather(id: Int): Observable<WeatherRequest> {
        return Observable.fromCallable {
            this.mWeatherRequestDao.getOneByID(id)
        }
    }

}