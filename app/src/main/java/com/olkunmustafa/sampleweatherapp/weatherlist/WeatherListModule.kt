package com.olkunmustafa.sampleweatherapp.weatherlist

import android.content.Context
import com.google.gson.Gson
import com.olkunmustafa.sampleweatherapp.data.weatherlist.IWeatherListUtil
import com.olkunmustafa.sampleweatherapp.data.weatherlist.WeatherListFromLocalDB
import com.olkunmustafa.sampleweatherapp.weatherlist.adapter.WeatherListAdapter
import dagger.Module
import dagger.Provides

@Module
class WeatherListModule {

    @Provides
    fun provideWeatherListAdapter(context: Context, gson: Gson?): WeatherListAdapter {
        return WeatherListAdapter(context, gson)
    }

    @Provides
    fun provideIWeatherListUtil(): IWeatherListUtil {
        return WeatherListFromLocalDB()
    }

}