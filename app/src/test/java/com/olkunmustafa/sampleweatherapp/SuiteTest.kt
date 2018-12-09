package com.olkunmustafa.sampleweatherapp

import com.olkunmustafa.sampleweatherapp.data.util.dateutil.FormatDateTest
import com.olkunmustafa.sampleweatherapp.data.util.temperatureutil.FormattedTemperatureTest
import com.olkunmustafa.sampleweatherapp.weatherdetail.util.ArgumentUtilTest
import com.olkunmustafa.sampleweatherapp.weatherdetail.util.checkweatherutil.CheckWeatherObject
import com.olkunmustafa.sampleweatherapp.weatherdetail.util.checkweatherutil.CheckWeatherObjectTest
import com.olkunmustafa.sampleweatherapp.weatherlist.WeatherListPresenterTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    FormatDateTest::class,
    FormattedTemperatureTest::class,
    WeatherListPresenterTest::class,
    ArgumentUtilTest::class,
    CheckWeatherObjectTest::class
)
class SuiteTest {
}
