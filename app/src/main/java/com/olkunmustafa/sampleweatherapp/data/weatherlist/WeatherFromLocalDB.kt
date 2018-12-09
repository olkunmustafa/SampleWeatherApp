package com.olkunmustafa.sampleweatherapp.data.weatherlist

import com.olkunmustafa.sampleweatherapp.data.storage.WeatherDatabase
import com.olkunmustafa.sampleweatherapp.data.storage.WeatherRequest
import com.olkunmustafa.sampleweatherapp.data.storage.WeatherRequestDao
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

public class WeatherFromLocalDB(weatherDatabase: WeatherDatabase) : IWeatherUtil {

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

    override fun saveWeatherRequest(weatherRequest: WeatherRequest) : Observable<Long> {
        return Observable.just(weatherRequest)
            .map {
                this.mWeatherRequestDao.insertAll(weatherRequest)
            }
    }
}