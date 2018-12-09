package com.olkunmustafa.sampleweatherapp.weatherlist

import android.content.Context
import com.olkunmustafa.sampleweatherapp.data.storage.WeatherRequest
import com.olkunmustafa.sampleweatherapp.weatherlist.adapter.WeatherListAdapter
import com.olkunmustafa.sampleweatherapp.weathermain.listener.IFragmentListener
import java.util.function.Consumer

interface IWeatherListContract {

    interface View {

        fun init()

        fun showEmptyListView()

        fun showWeatherList()

        fun setAdapter(adapter: WeatherListAdapter)

    }

    interface Presenter {

        fun setView(view: View)

        fun attached(context: Context?)

        fun created()

        fun started()

        fun stopped()

        fun destroyed()

        fun getWeatherListOnNext(weatherList: List<WeatherRequest>)

        fun getWeatherListOnError(throwable: Throwable)

    }

}