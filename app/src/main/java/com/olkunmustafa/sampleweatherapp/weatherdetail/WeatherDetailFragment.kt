package com.olkunmustafa.sampleweatherapp.weatherdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.olkunmustafa.sampleweatherapp.AppModule
import com.olkunmustafa.sampleweatherapp.BaseFragment
import com.olkunmustafa.sampleweatherapp.R
import com.olkunmustafa.sampleweatherapp.weatherlist.WeatherListFragment
import javax.inject.Inject

class WeatherDetailFragment : BaseFragment(), IWeatherDetailContract.View {

    @Inject
    lateinit var presenter: WeatherDetailPresenter

    companion object {
        fun newInstance( id : Int ): WeatherDetailFragment {
            val args = Bundle()
            val fragment = WeatherDetailFragment()

            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerWeatherDetailComponent.builder()
            .appModule(AppModule(activity!!))
            .weatherDetailModule( WeatherDetailModule() )
            .build()
            .inject(this)

        this.presenter.setView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val rootView = inflater.inflate(R.layout.fragment_weather_detail, container, false)

        ButterKnife.bind(this, rootView)
        return rootView
    }

}