package com.olkunmustafa.sampleweatherapp.weatherdetail

interface IWeatherDetailContract {

    interface View {

    }

    interface Presenter {


        fun setView(view: View)

        fun created( id : Int )
    }

}