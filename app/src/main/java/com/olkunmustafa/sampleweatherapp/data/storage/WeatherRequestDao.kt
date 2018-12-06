package com.olkunmustafa.sampleweatherapp.data.storage

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface WeatherRequestDao {

    @Query("SELECT * FROM weatherrequest")
    fun getAll(): List<WeatherRequest>

    @Insert
    fun insertAll(request: WeatherRequest) : Long

    @Query("SELECT * FROM weatherrequest where id = :id")
    fun getOneByID( id : Int ): WeatherRequest

}