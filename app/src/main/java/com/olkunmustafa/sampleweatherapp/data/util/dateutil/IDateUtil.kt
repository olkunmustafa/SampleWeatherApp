package com.olkunmustafa.sampleweatherapp.data.util.dateutil

import java.sql.Date

interface IDateUtil {

    /**
     * Converts the given date to
     * Human Approach date view
     *
     * @since 0.1
     * @author Mustafa Olkun
     */
    fun formatDate(date: Date?): String

    /**
     * Converts to HH:mm time type the given long value.
     *
     * @since 0.2
     * @author Mustafa Olkun
     */
    fun formatTimeFromSecond(time: Long): String

}