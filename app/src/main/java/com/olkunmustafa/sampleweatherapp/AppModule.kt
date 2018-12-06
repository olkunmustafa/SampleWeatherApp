package com.olkunmustafa.sampleweatherapp

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.olkunmustafa.sampleweatherapp.data.apiclient.ApiClient
import com.olkunmustafa.sampleweatherapp.data.storage.WeatherDatabase
import com.olkunmustafa.sampleweatherapp.data.util.dateutil.FormatDate
import com.olkunmustafa.sampleweatherapp.data.util.dateutil.IDateUtil
import com.olkunmustafa.sampleweatherapp.data.util.iconutil.IIconUtil
import com.olkunmustafa.sampleweatherapp.data.util.temperatureutil.FormattedTemperature
import com.olkunmustafa.sampleweatherapp.data.util.temperatureutil.ITemperatureUtil
import com.olkunmustafa.sampleweatherapp.data.weatherlist.FakeList
import com.olkunmustafa.sampleweatherapp.data.weatherlist.IWeatherUtil
import com.olkunmustafa.sampleweatherapp.weatherdetail.util.checkweatherutil.CheckWeatherObject
import com.olkunmustafa.sampleweatherapp.weatherdetail.util.checkweatherutil.ICheckWeatherUtil
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private var mContext: Context) {

    @Singleton
    @Provides
    fun provideContext(): Context {
        return this.mContext
    }

    @Singleton
    @Provides
    fun provideWeatherDatabase(context: Context): WeatherDatabase {
        return WeatherDatabase.getDatabase(context)
    }

    @Provides
    fun provideGson(): Gson? {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Provides
    fun provideIDateUtil(): IDateUtil {
        return FormatDate()
    }

    @Provides
    fun provideITemperatureUtil(): ITemperatureUtil {
        return FormattedTemperature()
    }

    @Provides
    fun provideICheckWeatherUtil(
        iconUtil: IIconUtil,
        iTemperatureUtil: ITemperatureUtil
    ): ICheckWeatherUtil {
        return CheckWeatherObject(
            iconUtil, iTemperatureUtil
        )
    }

    @Provides
    fun provideApiClient(): ApiClient {
        return ApiClient()
    }

    @Provides
    fun provideIWeatherListUtil(): IWeatherUtil {
//        return WeatherListFromLocalDB(weatherDatabase)
        return FakeList()
    }

}