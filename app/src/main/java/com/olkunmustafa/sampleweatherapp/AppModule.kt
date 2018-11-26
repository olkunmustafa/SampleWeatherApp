package com.olkunmustafa.sampleweatherapp

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@Singleton
class AppModule(var mContext: Context) {

    @Singleton
    @Provides
    fun provideContext(): Context {
        return this.mContext

    }

}