package com.olkunmustafa.sampleweatherapp.weatherdetail.util.checkweatherutil

import com.olkunmustafa.sampleweatherapp.data.util.iconutil.IIconUtil
import com.olkunmustafa.sampleweatherapp.data.util.temperatureutil.ITemperatureUtil
import com.olkunmustafa.sampleweatherapp.model.Main
import com.olkunmustafa.sampleweatherapp.model.Weather
import com.olkunmustafa.sampleweatherapp.model.Weather_
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

    @Mock
    lateinit var mockTemperatureUtil : ITemperatureUtil

    @Mock
    lateinit var mockMain : Main

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        this.checkWeatherObject = CheckWeatherObject(
            this.mockIconUtil,
            this.mockTemperatureUtil
        )

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

    @Test
    fun getCurrentTemperatureText_ShoudReturnError_IfTheMainObjectIsNull() {

        // Given
        val testObserver : TestObserver<String> = TestObserver()

        Mockito
            .doReturn(null)
            .`when`(this.mockWeather)
            .main

        // When
        this.checkWeatherObject
            .getCurrentTemperatureText( this.mockWeather )
            .subscribe(testObserver)

        // Then
        testObserver
            .assertNotComplete()
            .assertError( IllegalArgumentException::class.java )

    }

    @Test
    fun getCurrentTemperatureText_ShoudldReturnStyledTemperature_IfConditionsProvided() {

        // Given
        val testObserver : TestObserver<String> = TestObserver()

        Mockito
            .doReturn(this.mockMain)
            .`when`(this.mockWeather)
            .main

        Mockito
            .doReturn(10.0)
            .`when`(this.mockMain)
            .temp

        Mockito
            .doReturn("10C")
            .`when`(this.mockTemperatureUtil)
            .getStyledTemperature(Mockito.anyDouble())

        // When
        this.checkWeatherObject
            .getCurrentTemperatureText( this.mockWeather )
            .subscribe(testObserver)

        // Then
        testObserver
            .assertComplete()
            .assertValue("10C")

        Mockito
            .verify( this.mockTemperatureUtil )
            .getStyledTemperature( Mockito.anyDouble() )
    }
}