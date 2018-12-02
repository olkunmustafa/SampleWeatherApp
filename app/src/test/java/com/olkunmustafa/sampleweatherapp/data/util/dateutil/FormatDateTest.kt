package com.olkunmustafa.sampleweatherapp.data.util.dateutil

import org.assertj.core.api.Assertions
import org.junit.Test

import org.junit.Before
import java.sql.Date

class FormatDateTest {

    lateinit var iDateUtil: IDateUtil

    @Before
    fun setUp() {
        this.iDateUtil = FormatDate()
    }

    @Test
    fun formatDate_ShouldReturnStringDataFromDateObject() {

        // Given
        val givenTimeStamp = 1543768462000L
        val expected = "02-12-2018"
        val fakeDate = Date(givenTimeStamp)

        // When
        val actual = this.iDateUtil.formatDate(fakeDate)

        // Then
        Assertions
            .assertThat( actual )
            .isEqualTo(expected)

    }
}