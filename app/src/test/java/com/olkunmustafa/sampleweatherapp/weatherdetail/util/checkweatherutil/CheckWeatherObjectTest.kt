package com.olkunmustafa.sampleweatherapp.weatherdetail.util.checkweatherutil

import com.olkunmustafa.sampleweatherapp.data.util.iconutil.IIconUtil
import com.olkunmustafa.sampleweatherapp.model.Weather
import com.olkunmustafa.sampleweatherapp.model.Weather_
import io.reactivex.functions.Predicate
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CheckWeatherObjectTest {

    private lateinit var checkWeatherObject: CheckWeatherObject

    @Mock
    lateinit var mockIconUtil: IIconUtil

    @Mock
    lateinit var mockWeather : Weather

    @Mock
    lateinit var mockWeatherList : List<Weather_>

    @Mock
    lateinit var mockWeather_ : Weather_

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        this.checkWeatherObject = CheckWeatherObject(this.mockIconUtil)

        Mockito
            .doReturn( this.mockWeatherList )
            .`when`(this.mockWeather)
            .weather
    }

    @Test
    fun getTemperatureIconUrl_ShouldError_IfWeatherSizeEqualsZero() {

        // Given
        val testObserver : TestObserver<String> = TestObserver()

        Mockito
            .doReturn( 0 )
            .`when`(this.mockWeatherList)
            .size

        // When
        this.checkWeatherObject
            .getTemperatureIconUrl( this.mockWeather )
            .subscribe(testObserver)

        // Then
        testObserver
            .assertNotComplete()
            .assertError( IllegalArgumentException::class.java )

    }

    @Test
    fun getTemperatureIconUrl_ShouldErrorIfIconIsEmpty() {

        // Given
        val testObserver : TestObserver<String> = TestObserver()

        Mockito
            .doReturn( 1 )
            .`when`(this.mockWeatherList)
            .size

        Mockito
            .doReturn( "" )
            .`when`(this.mockWeather_)
            .icon

        Mockito
            .doReturn( this.mockWeather_ )
            .`when`(this.mockWeatherList)[0]

        // When
        this.checkWeatherObject
            .getTemperatureIconUrl( this.mockWeather )
            .subscribe(testObserver)

        // Then
        testObserver
            .assertNotComplete()
            .assertError( IllegalArgumentException::class.java )

    }

    @Test
    fun getTemperatureIconUrl_ShouldReturnIconFullUrl_IfConditionsAreSuitable() {

        // Given
        val testObserver : TestObserver<String> = TestObserver()

        Mockito
            .doReturn( 1 )
            .`when`(this.mockWeatherList)
            .size

        Mockito
            .doReturn( "01d" )
            .`when`(this.mockWeather_)
            .icon

        Mockito
            .doReturn( this.mockWeather_ )
            .`when`(this.mockWeatherList)[0]

        Mockito
            .doReturn("uri_Contains_01d")
            .`when`( this.mockIconUtil )
            .getIconFullUrl(Mockito.anyString())

        // When
        this.checkWeatherObject
            .getTemperatureIconUrl( this.mockWeather )
            .subscribe(testObserver)

        // Then
        testObserver
            .assertComplete()
            .assertNoErrors()
            .assertValue { it -> it.contains("01d") }

        Mockito
            .verify( this.mockIconUtil )
            .getIconFullUrl(Mockito.anyString())

    }
}