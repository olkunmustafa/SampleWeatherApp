package com.olkunmustafa.sampleweatherapp.weathermain

interface IWeatherContract {

    interface View {

        fun replaceFragment(): Unit

    }

    interface Presenter {

        fun setView(view: View)

        fun created(): Unit

    }

}