package com.olkunmustafa.sampleweatherapp.weathermain

import android.content.Intent

interface IWeatherContract {

    interface View {

        fun replaceFragment()

    }

    interface Presenter {

        fun setView(view: View)

        fun created()

        fun saveButtonClicked()

        fun searchDeviceLocation()

        fun activityResult( requestCode: Int, resultCode: Int, data: Intent? )
    }

}