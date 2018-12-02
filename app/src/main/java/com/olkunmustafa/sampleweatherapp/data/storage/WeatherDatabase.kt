package com.olkunmustafa.sampleweatherapp.data.storage

import android.arch.persistence.room.*
import android.content.Context

@Database(
    entities = [WeatherRequest::class],
    version = 1
)
@TypeConverters(Converter::class)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherModel(): WeatherRequestDao

    companion object {

        var INSTANCE: WeatherDatabase? = null

        // Creates database.
        fun getDatabase(context: Context): WeatherDatabase {

            if (INSTANCE == null) {
                INSTANCE = Room.inMemoryDatabaseBuilder(context, WeatherDatabase::class.java)
                    .build()

            }

            return INSTANCE!!
        }

    }

    fun destroyInstance() {
        INSTANCE = null

    }
}