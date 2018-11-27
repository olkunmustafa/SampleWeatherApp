package com.olkunmustafa.sampleweatherapp.weatherlist

import com.olkunmustafa.sampleweatherapp.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, WeatherListModule::class])
 interface WeatherListComponent {

    fun inject(fragment: WeatherFragment): Unit

}