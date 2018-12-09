package com.olkunmustafa.sampleweatherapp.weatherdetail.util

import android.os.Bundle
import com.olkunmustafa.sampleweatherapp.weatherdetail.WeatherDetailConstants
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ArgumentUtilTest {

    lateinit var argumentUtil: ArgumentUtil

    var fakeBundle : Bundle? = null

    @Mock
    lateinit var mockBundle : Bundle

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.argumentUtil = ArgumentUtil()
    }

    @Test
    fun checkArgsNotNull_ShouldReturnErrorIfBundleNull() {

        // Given
        val testObserver : TestObserver<Bundle> = TestObserver()

        // When
        this.argumentUtil
            .checkArgsNotNull( this.fakeBundle )
            .subscribe( testObserver )

        // Then
        testObserver
            .assertNotComplete()
            .assertError(NoSuchElementException::class.java)
    }

    @Test
    fun checkArgsNotNull_ShouldReturnGivenNull() {

        // Given
        val testObserver : TestObserver<Bundle> = TestObserver()
        this.fakeBundle = Bundle()

        // When
        this.argumentUtil
            .checkArgsNotNull( this.fakeBundle )
            .subscribe( testObserver )

        // Then
        testObserver
            .assertComplete()

    }

    @Test
    fun checkArgRecordContains_ShouldThrowError_IfBundleNoContatainsRecordIDKey() {

        // Given
        val testObserver : TestObserver<Int> = TestObserver()
        Mockito
            .doReturn( false )
            .`when`(this.mockBundle)
            .containsKey(  WeatherDetailConstants.RECORD_ID )

        // When
        this.argumentUtil
            .checkArgRecordContains( this.mockBundle )
            .subscribe(testObserver)

        // Then
        testObserver
            .assertNotComplete()
            .assertError( NoSuchElementException::class.java )
            .assertErrorMessage("To call the selected weather from local database, RecordID should not be null")

    }

    @Test
    fun checkArgRecordContains_ShouldReturnGivenKey() {

        // Given
        val testObserver : TestObserver<Int> = TestObserver()

        Mockito
            .doReturn( true )
            .`when`(this.mockBundle)
            .containsKey(  WeatherDetailConstants.RECORD_ID )

        Mockito
            .doReturn( 1 )
            .`when`(this.mockBundle)
            .getInt(  WeatherDetailConstants.RECORD_ID )

        // When
        this.argumentUtil
            .checkArgRecordContains( this.mockBundle )
            .subscribe(testObserver)

        // Then
        testObserver
            .assertComplete()
            .assertValue(1)

    }
}