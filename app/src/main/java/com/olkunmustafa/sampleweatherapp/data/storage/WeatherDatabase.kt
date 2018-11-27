package com.olkunmustafa.sampleweatherapp.data.storage

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Room
import android.content.Context

abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherModel(): WeatherRequestDao

    companion object {

        var INSTANCE : WeatherDatabase? = null

        // Creates database.
        fun getDatabase(context: Context): WeatherDatabase {

            if( INSTANCE == null ){
                Room.inMemoryDatabaseBuilder( context, WeatherDatabase::class.java )
                    .build()

            }

            return INSTANCE!!
        }

    }

    fun destroyInstance() {
        INSTANCE = null

    }
}