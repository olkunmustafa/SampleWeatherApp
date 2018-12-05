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

        fun locationRequest()

        fun activityResult( requestCode: Int, resultCode: Int, data: Intent? )
    }

}