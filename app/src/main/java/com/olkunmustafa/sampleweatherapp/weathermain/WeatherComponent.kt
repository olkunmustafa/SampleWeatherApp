package com.olkunmustafa.sampleweatherapp.weathermain

import com.olkunmustafa.sampleweatherapp.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, WeatherModule::class])
interface WeatherComponent {

    fun inject(activity: WeatherActivity)

}