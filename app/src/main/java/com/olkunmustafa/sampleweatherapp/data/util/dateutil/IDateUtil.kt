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
    fun formatDate(date: Date): String

}