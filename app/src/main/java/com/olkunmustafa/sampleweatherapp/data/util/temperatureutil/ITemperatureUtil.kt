package com.olkunmustafa.sampleweatherapp.data.util.temperatureutil

interface ITemperatureUtil {

    /**
     * @since 0.2
     * @author Mustafa Olkun
     * @return Styled human understand temperature value.
     */
    fun getStyledTemperature(temperature: Double): String

    /**
     * @since 0.2
     * @author Mustafa Olkun
     * @return Styled human understand temperature value.
     */
    fun getStyledMinMaxTemperature(min: Double, max: Double): String
    
}