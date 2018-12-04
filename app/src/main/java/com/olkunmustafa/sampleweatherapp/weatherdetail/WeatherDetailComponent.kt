package com.olkunmustafa.sampleweatherapp.weatherdetail

import com.olkunmustafa.sampleweatherapp.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = [AppModule::class, WeatherDetailModule::class] )
interface WeatherDetailComponent {

    fun inject(fragment: WeatherDetailFragment)

}