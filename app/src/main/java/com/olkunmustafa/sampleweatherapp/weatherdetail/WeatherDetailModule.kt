package com.olkunmustafa.sampleweatherapp.weatherdetail

import com.google.gson.Gson
import com.olkunmustafa.sampleweatherapp.data.storage.WeatherDatabase
import com.olkunmustafa.sampleweatherapp.data.util.createmodel.CreateWeatherModelGson
import com.olkunmustafa.sampleweatherapp.data.util.createmodel.ICreateWeatherModel
import com.olkunmustafa.sampleweatherapp.data.util.iconutil.IIconUtil
import com.olkunmustafa.sampleweatherapp.data.util.iconutil.OpenWeatherMapIconUtil
import com.olkunmustafa.sampleweatherapp.data.weatherlist.FakeList
import com.olkunmustafa.sampleweatherapp.data.weatherlist.IWeatherUtil
import dagger.Module
import dagger.Provides

@Module
class WeatherDetailModule {

    @Provides
    fun provideICreateWeatherModel(gson: Gson?): ICreateWeatherModel {
        return CreateWeatherModelGson(gson)
    }

    @Provides
    fun provideIWeatherListUtil(weatherDatabase: WeatherDatabase): IWeatherUtil {
//        return WeatherListFromLocalDB(weatherDatabase)
        return FakeList()
    }

    @Provides
    fun provideIIconUtil(): IIconUtil {
        return OpenWeatherMapIconUtil()
    }
}