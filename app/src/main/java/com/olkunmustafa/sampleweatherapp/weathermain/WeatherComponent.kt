package com.olkunmustafa.sampleweatherapp.weathermain

import com.olkunmustafa.sampleweatherapp.AppModule
import dagger.Component

@Component(modules = [AppModule::class])
interface WeatherComponent {

    fun inject(activity: WeatherActivity)

}