package com.olkunmustafa.sampleweatherapp.weatherdetail

import javax.inject.Inject

class WeatherDetailPresenter @Inject constructor() : IWeatherDetailContract.Presenter {

    override fun setView(view: IWeatherDetailContract.View) {
    }

    override fun created(id: Int) {
    }
}