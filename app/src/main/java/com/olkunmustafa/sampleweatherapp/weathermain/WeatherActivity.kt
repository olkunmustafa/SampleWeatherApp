package com.olkunmustafa.sampleweatherapp.weathermain

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.olkunmustafa.sampleweatherapp.AppModule
import com.olkunmustafa.sampleweatherapp.R
import com.olkunmustafa.sampleweatherapp.weatherdetail.WeatherDetailFragment
import com.olkunmustafa.sampleweatherapp.weatherlist.WeatherListFragment
import com.olkunmustafa.sampleweatherapp.weathermain.listener.IFragmentListener
import javax.inject.Inject

class WeatherActivity : AppCompatActivity(), IWeatherContract.View, IFragmentListener {

    @Inject
    lateinit var presenter : WeatherPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerWeatherComponent.builder()
            .appModule(AppModule(this))
            .build()
            .inject( this )

        this.presenter.setView(this)
        this.presenter.created()
    }

    override fun replaceFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, WeatherListFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    override fun openDetailFragment(dataID: Int) {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, WeatherDetailFragment.newInstance(dataID))
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            super.onBackPressed()

        } else {
            this.finish()

        }
    }
}
