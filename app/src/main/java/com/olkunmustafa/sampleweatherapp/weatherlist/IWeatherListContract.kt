package com.olkunmustafa.sampleweatherapp.weatherlist

import com.olkunmustafa.sampleweatherapp.data.storage.WeatherRequest
import com.olkunmustafa.sampleweatherapp.weatherlist.adapter.WeatherListAdapter
import java.util.function.Consumer

interface IWeatherListContract {

    interface View {

        fun init()

        fun showLoading()

        fun showEmptyListView()

        fun showAdapter()

        fun setAdapter( adapter : WeatherListAdapter )

    }

    interface Presenter {

        fun setView(view: View)

        fun created()

        fun destroyed()

        fun getWeatherListOnNext( weatherList : List<WeatherRequest>)

        fun getWeatherListOnError( throwable: Throwable )

    }

}