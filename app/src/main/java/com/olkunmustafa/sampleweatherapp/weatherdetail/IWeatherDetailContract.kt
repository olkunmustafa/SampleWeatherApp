package com.olkunmustafa.sampleweatherapp.weatherdetail

import android.os.Bundle
import com.olkunmustafa.sampleweatherapp.model.Weather
import io.reactivex.Observable

interface IWeatherDetailContract {

    interface View {

        fun setTemperatureIcon(url: String)

        fun setCurrentTemperature(temperature: String)

        fun setCurrentMinMax(minMax: String)

        fun setLocation(location: String)

        fun setRequestTime(time: String)

        fun setDescription(description: String)

        fun setWindSpeed(speed: String)

        fun setWindDegree(degree: String)

        fun setHumidity(humidity: String)

        fun setSunrise(sunrise: String)

        fun setSunset(sunset: String)

        fun setVisibility( visibility : String )

    }

    interface Presenter {


        fun setView(view: View)

        fun created(args: Bundle?)

        fun destroyed()

        fun weatherRequestOnNext( weather : Weather )

        fun temperatureIcon()

        fun mainTemperature()

        fun minMaxTemperature()

        fun location()

        fun requestTime()

        fun description()

        fun wind()

        fun humidity()

        fun sunTimes()

        fun visibility()
    }

}