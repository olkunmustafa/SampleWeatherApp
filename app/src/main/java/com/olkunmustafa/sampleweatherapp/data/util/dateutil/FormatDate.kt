package com.olkunmustafa.sampleweatherapp.data.util.dateutil

import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*

class FormatDate : IDateUtil {

    override fun formatDate(date: Date?): String {
        val format = SimpleDateFormat("dd MMMM YYY", Locale.getDefault())
        return format.format(date)
    }

}