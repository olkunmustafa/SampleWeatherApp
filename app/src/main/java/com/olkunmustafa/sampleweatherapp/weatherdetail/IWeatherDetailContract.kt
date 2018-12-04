package com.olkunmustafa.sampleweatherapp.weatherdetail

import android.os.Bundle
import io.reactivex.Observable

interface IWeatherDetailContract {

    interface View {

    }

    interface Presenter {


        fun setView(view: View)

        fun created( args : Bundle? )

    }

}