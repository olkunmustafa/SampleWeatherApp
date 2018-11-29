package com.olkunmustafa.sampleweatherapp.weatherlist

import com.olkunmustafa.sampleweatherapp.data.storage.WeatherRequest
import java.util.function.Consumer

interface IWeatherListContract {

    interface View {

        fun showLoading()

        fun showEmptyListView()

    }

    interface Presenter {

        fun setView(view: View)

        fun created()

        fun destroyed()

        fun getWeatherListOnNext() : Consumer<List<WeatherRequest>>

        fun getWeatherListOnError() : Consumer<Throwable>

    }

}