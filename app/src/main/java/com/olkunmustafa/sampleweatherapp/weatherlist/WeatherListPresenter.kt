package com.olkunmustafa.sampleweatherapp.weatherlist

import javax.inject.Inject

class WeatherListPresenter @Inject constructor() : IWeatherListContract.Presenter {

    override fun setView(view: IWeatherListContract.View) {
    }

    override fun created() {
    }

    override fun destroyed() {
    }
}