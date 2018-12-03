package com.olkunmustafa.sampleweatherapp.weatherdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.olkunmustafa.sampleweatherapp.AppModule
import com.olkunmustafa.sampleweatherapp.BaseFragment
import javax.inject.Inject

class WeatherDetailFragment : BaseFragment(), IWeatherDetailContract.View {

    @Inject
    lateinit var presenter: WeatherDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerWeatherDetailComponent.builder()
            .appModule(AppModule(activity!!))
            .weatherDetailModule( WeatherDetailModule() )
            .build()
            .inject(this)

        this.presenter.setView(this)
    }

}