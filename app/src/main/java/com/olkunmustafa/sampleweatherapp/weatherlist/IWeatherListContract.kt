package com.olkunmustafa.sampleweatherapp.weatherlist

interface IWeatherListContract {

    interface View {

        fun showLoading()

        fun showEmptyListView()

    }

    interface Presenter {

        fun setView(view: View)

        fun created()

        fun destroyed()

    }

}