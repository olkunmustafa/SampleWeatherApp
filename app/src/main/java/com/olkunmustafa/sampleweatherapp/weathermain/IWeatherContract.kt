package com.olkunmustafa.sampleweatherapp.weathermain

import android.content.Intent

interface IWeatherContract {

    interface View {

        fun showLoading()

        fun hideLoading()

        fun replaceFragment()

        fun showErrorDialog( message : Int )

    }

    interface Presenter {

        fun setView(view: View)

        fun created()

        fun saveButtonClicked()

        fun locationRequest()

        fun requestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    }

}