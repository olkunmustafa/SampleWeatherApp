package com.olkunmustafa.sampleweatherapp.weatherlist

import android.content.Context
import com.olkunmustafa.sampleweatherapp.weatherlist.adapter.WeatherListAdapter
import dagger.Module
import dagger.Provides

@Module
class WeatherListModule {

    @Provides
    fun provideWeatherListAdapter(context: Context): WeatherListAdapter {
        return WeatherListAdapter(context)
    }

}