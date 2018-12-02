package com.olkunmustafa.sampleweatherapp.weathermain

import javax.inject.Inject

class WeatherPresenter @Inject constructor() : IWeatherContract.Presenter {

    private lateinit var view : IWeatherContract.View

    override fun setView(view: IWeatherContract.View) {
        this.view = view
    }

    override fun created() {
        this.view.replaceFragment()
    }

}