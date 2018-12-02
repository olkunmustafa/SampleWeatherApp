package com.olkunmustafa.sampleweatherapp.data.storage

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.olkunmustafa.sampleweatherapp.model.Weather
import java.sql.Date
import java.time.Instant

@Entity(tableName = "weatherrequest")
class WeatherRequest {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var uid: Int = 0

    @ColumnInfo(name = "requesttime")
    lateinit var requestTime: Date

    @ColumnInfo(name = "weatherdata")
    lateinit var weatherdata: String

}