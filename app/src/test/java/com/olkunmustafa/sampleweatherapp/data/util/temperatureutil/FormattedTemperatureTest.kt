package com.olkunmustafa.sampleweatherapp.data.util.temperatureutil

import org.junit.Before
import org.junit.Test
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.assertj.core.api.Assertions
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
class FormattedTemperatureTest {

    lateinit var formattedTemperature: FormattedTemperature

    @Before
    fun setUp() {
        this.formattedTemperature = FormattedTemperature()

    }

    @Test
    @Parameters(method = "getTemperatureParameters")
    fun getStyledTemperature_ShouldReturnAsCelciusValue( given1 : Double, expected : String ) {

        // When
        val actual= this.formattedTemperature.getStyledTemperature( given1 )

        // Then
        Assertions
            .assertThat( actual )
            .isNotNull()
            .isNotBlank()
            .isEqualTo(expected)
    }
    private fun getTemperatureParameters(): Any {
        return arrayOf(
            arrayOf(7.5, "8°C"),
            arrayOf(7, "7°C"),
            arrayOf(7.0, "7°C"),
            arrayOf(7.9, "8°C"),
            arrayOf(7.6, "8°C"),
            arrayOf(7.2, "7°C"),
            arrayOf(7.4, "7°C")
        )
    }

    @Test
    @Parameters(method = "getTemperatureMinMaxParameters")
    fun getStyledMinMaxTemperature_ShouldReturnSuitableMinAndMax( given1 : Double, given2 : Double, expected : String ) {

        // When
        val actual= this.formattedTemperature.getStyledMinMaxTemperature( given1, given2 )

        // Then
        Assertions
            .assertThat( actual )
            .isNotNull()
            .isNotBlank()
            .isEqualTo(expected)
    }
    private fun getTemperatureMinMaxParameters(): Any {
        return arrayOf(
            arrayOf(7.5, 8.5, "8°/9°"),
            arrayOf(7.2, 8.8, "7°/9°"),
            arrayOf(7.0, 8.1, "7°/8°"),
            arrayOf(7.9, 8.1, "8°/8°"),
            arrayOf(7.6, 8.6, "8°/9°")
        )
    }
}