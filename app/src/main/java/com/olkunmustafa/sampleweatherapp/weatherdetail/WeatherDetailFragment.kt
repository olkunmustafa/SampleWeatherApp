package com.olkunmustafa.sampleweatherapp.weatherdetail

import android.os.Bundle
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.olkunmustafa.sampleweatherapp.AppModule
import com.olkunmustafa.sampleweatherapp.BaseFragment
import com.olkunmustafa.sampleweatherapp.R
import com.olkunmustafa.sampleweatherapp.weatherdetail.view.DetailItemView
import com.olkunmustafa.sampleweatherapp.weatherlist.WeatherListFragment
import javax.inject.Inject
import kotlin.math.min

class WeatherDetailFragment : BaseFragment(), IWeatherDetailContract.View {

    @Inject
    lateinit var presenter: WeatherDetailPresenter

    @BindView(R.id.temperature_icon)
    lateinit var temperatureIcon : AppCompatImageView

    @BindView(R.id.current_temperature)
    lateinit var currentTemperature : AppCompatTextView

    @BindView(R.id.current_min_max)
    lateinit var currentMinMax : AppCompatTextView

    @BindView(R.id.location)
    lateinit var location : AppCompatTextView

    @BindView(R.id.request_time)
    lateinit var requestTime : AppCompatTextView

    @BindView(R.id.description)
    lateinit var description : AppCompatTextView

    @BindView(R.id.wind_speed)
    lateinit var windSpeed : DetailItemView

    @BindView(R.id.wind_degree)
    lateinit var windDegree : DetailItemView

    @BindView(R.id.humidity)
    lateinit var humidity : DetailItemView

    @BindView(R.id.sunrise)
    lateinit var sunrise : DetailItemView

    @BindView(R.id.sunset)
    lateinit var sunset : DetailItemView

    @BindView(R.id.visibility)
    lateinit var visibility : DetailItemView

    companion object {
        fun newInstance( id : Int ): WeatherDetailFragment {
            val args = Bundle()
            val fragment = WeatherDetailFragment()
            args.putInt(WeatherDetailConstants.RECORD_ID, id)

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

        this.presenter.created( arguments )
        return rootView
    }

    override fun setTemperatureIcon(url: String) {
    }

    override fun setCurrentTemperature(temperature: String) {
        this.currentTemperature.text = temperature
    }

    override fun setCurrentMinMax(minMax: String) {
        this.currentMinMax.text = minMax
    }

    override fun setLocation(location: String) {
        this.location.text = location
    }

    override fun setRequestTime(time: String) {
        this.requestTime.text = time
    }

    override fun setDescription(description: String) {
        this.description.text = description
    }

    override fun setWindSpeed(speed: String) {
        this.windSpeed.setDetailValue(speed)
    }

    override fun setWindDegree(degree: String) {
        this.windDegree.setDetailValue(degree)
    }

    override fun setHumidity(humidity: String) {
        this.humidity.setDetailValue(humidity)
    }

    override fun setSunrise(sunrise: String) {
        this.sunrise.setDetailValue(sunrise)
    }

    override fun setSunset(sunset: String) {
        this.sunset.setDetailValue(sunset)
    }

    override fun setVisibility(visibility: String) {
        this.visibility.setDetailValue(visibility)
    }


}