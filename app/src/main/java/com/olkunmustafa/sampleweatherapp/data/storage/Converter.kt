package com.olkunmustafa.sampleweatherapp.data.storage

import android.arch.persistence.room.TypeConverter
import java.sql.Date

class Converter {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}