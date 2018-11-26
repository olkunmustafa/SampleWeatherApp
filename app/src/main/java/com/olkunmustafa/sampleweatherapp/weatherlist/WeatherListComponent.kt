package com.olkunmustafa.sampleweatherapp.weatherlist

import com.olkunmustafa.sampleweatherapp.AppModule
import dagger.Component

@Component(modules = [AppModule::class, WeatherListModule::class])
interface WeatherListComponent {

}