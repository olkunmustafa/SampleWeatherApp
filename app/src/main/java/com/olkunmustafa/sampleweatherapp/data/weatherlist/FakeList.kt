package com.olkunmustafa.sampleweatherapp.data.weatherlist

import com.olkunmustafa.sampleweatherapp.data.storage.WeatherRequest
import io.reactivex.Observable
import java.sql.Date
import java.util.*

class FakeList : IWeatherUtil {

    val test = "{\n" +
            "    \"coord\": {\n" +
            "        \"lon\": 28.95,\n" +
            "        \"lat\": 41.01\n" +
            "    },\n" +
            "    \"weather\": [\n" +
            "        {\n" +
            "            \"id\": 803,\n" +
            "            \"main\": \"Clouds\",\n" +
            "            \"description\": \"broken clouds\",\n" +
            "            \"icon\": \"04d\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"base\": \"stations\",\n" +
            "    \"main\": {\n" +
            "        \"temp\": 10.42,\n" +
            "        \"pressure\": 1019,\n" +
            "        \"humidity\": 76,\n" +
            "        \"temp_min\": 9,\n" +
            "        \"temp_max\": 12\n" +
            "    },\n" +
            "    \"visibility\": 10000,\n" +
            "    \"wind\": {\n" +
            "        \"speed\": 3.1,\n" +
            "        \"deg\": 60\n" +
            "    },\n" +
            "    \"clouds\": {\n" +
            "        \"all\": 75\n" +
            "    },\n" +
            "    \"dt\": 1543821600,\n" +
            "    \"sys\": {\n" +
            "        \"type\": 1,\n" +
            "        \"id\": 6970,\n" +
            "        \"message\": 0.1832,\n" +
            "        \"country\": \"TR\",\n" +
            "        \"sunrise\": 1543813907,\n" +
            "        \"sunset\": 1543847769\n" +
            "    },\n" +
            "    \"id\": 745044,\n" +
            "    \"name\": \"Ankara\",\n" +
            "    \"cod\": 200\n" +
            "}"

    override fun getWeatherRequestList(): Observable<List<WeatherRequest>> {

        val weatherRequest : WeatherRequest = WeatherRequest()
        weatherRequest.requestTime = Date(Calendar.getInstance().timeInMillis)
        weatherRequest.weatherdata = test
        weatherRequest.uid = 1

       val fakeList : ArrayList<WeatherRequest> = ArrayList()
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )
       fakeList.add(weatherRequest )

        return Observable.just( fakeList )
    }

   override fun getWeather(id: Int): Observable<WeatherRequest> {
      val weatherRequest : WeatherRequest = WeatherRequest()
      weatherRequest.requestTime = Date(Calendar.getInstance().timeInMillis )
      weatherRequest.weatherdata = test
      weatherRequest.uid = 1

      return Observable.just( weatherRequest )
   }

}