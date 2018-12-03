package com.olkunmustafa.sampleweatherapp.weatherdetail

import com.olkunmustafa.sampleweatherapp.AppModule
import dagger.Component

@Component( modules = [AppModule::class, WeatherDetailModule::class] )
interface WeatherDetailComponent {

    fun inject(fragment: WeatherDetailFragment)

}