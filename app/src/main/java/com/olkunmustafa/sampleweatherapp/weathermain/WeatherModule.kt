package com.olkunmustafa.sampleweatherapp.weathermain

import android.app.Activity
import com.google.gson.Gson
import com.olkunmustafa.sampleweatherapp.data.util.createmodel.CreateWeatherModelGson
import com.olkunmustafa.sampleweatherapp.data.util.createmodel.ICreateWeatherModel
import com.olkunmustafa.sampleweatherapp.permissions.location.AccessLocation
import com.olkunmustafa.sampleweatherapp.permissions.location.IAccessLocationUtil
import com.olkunmustafa.sampleweatherapp.util.location.KLocationSettingsHelper
import com.olkunmustafa.sampleweatherapp.util.location.LocationServiceUtil
import dagger.Module
import dagger.Provides

@Module
class WeatherModule(private var activity: Activity) {

    @Provides
    fun provideIAccessLocationUtil(): IAccessLocationUtil {
        return AccessLocation(this.activity)
    }

    @Provides
    fun provideLocationServiceUtil(): LocationServiceUtil {
        return LocationServiceUtil(this.activity)
    }

    @Provides
    fun provideKLocationSettingsHelper() : KLocationSettingsHelper {
        return KLocationSettingsHelper(this.activity)
    }

    @Provides
    fun provideICreateWeatherModel(gson: Gson?): ICreateWeatherModel {
        return CreateWeatherModelGson(gson)
    }
}