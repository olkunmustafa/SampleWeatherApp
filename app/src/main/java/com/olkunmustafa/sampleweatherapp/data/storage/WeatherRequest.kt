package com.olkunmustafa.sampleweatherapp.data.storage

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "weatherrequest")
class WeatherRequest {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var uid: Int = 0

    @ColumnInfo(name = "requesttime")
    var requestTime: Date? = null

    @ColumnInfo(name = "weatherdata")
    var weatherdata: String? = null

}