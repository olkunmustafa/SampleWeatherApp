package com.olkunmustafa.sampleweatherapp.weatherlist

import android.content.Context
import com.google.gson.Gson
import com.olkunmustafa.sampleweatherapp.data.storage.WeatherDatabase
import com.olkunmustafa.sampleweatherapp.data.util.createmodel.CreateWeatherModelGson
import com.olkunmustafa.sampleweatherapp.data.util.createmodel.ICreateWeatherModel
import com.olkunmustafa.sampleweatherapp.data.util.dateutil.IDateUtil
import com.olkunmustafa.sampleweatherapp.data.weatherlist.FakeList
import com.olkunmustafa.sampleweatherapp.data.weatherlist.IWeatherListUtil
import com.olkunmustafa.sampleweatherapp.data.weatherlist.WeatherListFromLocalDB
import com.olkunmustafa.sampleweatherapp.weatherlist.adapter.WeatherListAdapter
import dagger.Module
import dagger.Provides

@Module
class WeatherListModule {

    @Provides
    fun provideWeatherListAdapter(
        context: Context,
        iCreateWeatherModel: ICreateWeatherModel,
        iDateUtil: IDateUtil
    ): WeatherListAdapter {
        return WeatherListAdapter(context, iCreateWeatherModel, iDateUtil)
    }

    @Provides
    fun provideIWeatherListUtil(weatherDatabase: WeatherDatabase): IWeatherListUtil {
        return FakeList()
    }

    @Provides
    fun provideICreateWeatherModel(gson: Gson?): ICreateWeatherModel {
        return CreateWeatherModelGson(gson)
    }

}