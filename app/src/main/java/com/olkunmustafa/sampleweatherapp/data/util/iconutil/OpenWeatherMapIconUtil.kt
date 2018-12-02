package com.olkunmustafa.sampleweatherapp.data.util.iconutil

class OpenWeatherMapIconUtil : IIconUtil {

    override fun getIconFullUrl(icon: String): String {
        return "http://openweathermap.org/img/w/$icon.png"
    }

}