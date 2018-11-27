package com.olkunmustafa.sampleweatherapp.weatherlist

import android.os.Bundle
import com.olkunmustafa.sampleweatherapp.AppModule
import com.olkunmustafa.sampleweatherapp.BaseFragment
import javax.inject.Inject

class WeatherFragment : BaseFragment(), IWeatherListContract.View {

    @Inject
    lateinit var mPresenter: WeatherListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerWeatherListComponent.builder()
            .appModule(AppModule(activity!!))
            .build()
            .inject( this )

    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showEmptyListView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}