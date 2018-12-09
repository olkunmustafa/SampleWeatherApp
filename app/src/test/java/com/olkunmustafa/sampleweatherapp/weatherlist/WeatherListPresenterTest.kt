package com.olkunmustafa.sampleweatherapp.weatherlist

import com.olkunmustafa.sampleweatherapp.data.storage.WeatherRequest
import com.olkunmustafa.sampleweatherapp.weatherlist.adapter.WeatherListAdapter
import io.reactivex.observers.TestObserver
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.Spy

class WeatherListPresenterTest {

    @Spy
    lateinit var weatherListPresenter: WeatherListPresenter

    @Mock
    lateinit var mockWeatherRequestList : List<WeatherRequest>

    @Mock
    lateinit var mockWeatherListAdapter: WeatherListAdapter

    @Mock
    lateinit var mockView : IWeatherListContract.View

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        this.weatherListPresenter.setView( this.mockView )
        this.weatherListPresenter.weatherListAdapter = this.mockWeatherListAdapter
    }

    @Test
    fun getWeatherListOnNext_ShouldShowEmptyText_IfSizeHigherThanZero() {

        // Given
        Mockito
            .doReturn( false )
            .`when`(this.mockWeatherRequestList)
            .isEmpty()

        // When
        this.weatherListPresenter
            .getWeatherListOnNext( this.mockWeatherRequestList )

        // Then
        Mockito
            .verify( this.mockView )
            .setAdapter( this.mockWeatherListAdapter )

    }

    @Test
    fun getWeatherListOnNext_ShouldShowEmptyText_IfSizeEqualsToZero() {

        // Given
        Mockito
            .doReturn( true )
            .`when`(this.mockWeatherRequestList)
            .isEmpty()

        // When
        this.weatherListPresenter
            .getWeatherListOnNext( this.mockWeatherRequestList )

        // Then
        Mockito
            .verify( this.mockView )
            .showEmptyListView()

    }
}
