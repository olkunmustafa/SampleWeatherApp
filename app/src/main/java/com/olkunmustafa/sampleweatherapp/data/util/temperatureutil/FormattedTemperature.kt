package com.olkunmustafa.sampleweatherapp.data.util.temperatureutil

class FormattedTemperature : ITemperatureUtil {

    override fun getStyledTemperature(temperature: Double): String {
        val rounded = Math.round( temperature )
        return rounded.toString() + "°C"
    }

    override fun getStyledMinMaxTemperature(min: Double, max: Double): String {
        val roundedMin = Math.round( min )
        val roundedMax = Math.round( max )

        return roundedMin.toString() + "°/" + roundedMax + "°"
    }
}