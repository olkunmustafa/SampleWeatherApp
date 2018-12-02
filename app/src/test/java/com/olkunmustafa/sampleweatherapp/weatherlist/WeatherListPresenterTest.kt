package com.olkunmustafa.sampleweatherapp.weatherlist

import io.reactivex.observers.TestObserver
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import org.mockito.Spy

class WeatherListPresenterTest {

    @Spy
    lateinit var weatherListPresenter: WeatherListPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getWeatherListOnNext_ShouldShowEmptyText_IfSizeEqualsToZero() {

        // Given


        // When

        // Then

    }
}
