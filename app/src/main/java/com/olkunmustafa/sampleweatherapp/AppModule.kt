package com.olkunmustafa.sampleweatherapp

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.olkunmustafa.sampleweatherapp.data.storage.WeatherDatabase
import com.olkunmustafa.sampleweatherapp.data.util.dateutil.FormatDate
import com.olkunmustafa.sampleweatherapp.data.util.dateutil.IDateUtil
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(var mContext: Context) {

    @Singleton
    @Provides
    fun provideContext(): Context {
        return this.mContext
    }

    @Singleton
    @Provides
    fun provideWeatherDatabase(context: Context): WeatherDatabase {
        return WeatherDatabase.getDatabase(context)
    }

    @Provides
    fun provideGson(): Gson? {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Provides
    fun provideIDateUtil(): IDateUtil {
        return FormatDate()
    }

}