package com.olkunmustafa.sampleweatherapp.data.weatherlist

import com.olkunmustafa.sampleweatherapp.data.storage.WeatherRequest
import io.reactivex.Observable
import java.sql.Date
import java.util.*

class FakeList : IWeatherListUtil {

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
            "        \"temp\": 285.62,\n" +
            "        \"pressure\": 1021,\n" +
            "        \"humidity\": 66,\n" +
            "        \"temp_min\": 285.15,\n" +
            "        \"temp_max\": 286.15\n" +
            "    },\n" +
            "    \"visibility\": 10000,\n" +
            "    \"wind\": {\n" +
            "        \"speed\": 4.6,\n" +
            "        \"deg\": 20\n" +
            "    },\n" +
            "    \"clouds\": {\n" +
            "        \"all\": 75\n" +
            "    },\n" +
            "    \"dt\": 1543751400,\n" +
            "    \"sys\": {\n" +
            "        \"type\": 1,\n" +
            "        \"id\": 6970,\n" +
            "        \"message\": 0.0031,\n" +
            "        \"country\": \"TR\",\n" +
            "        \"sunrise\": 1543727458,\n" +
            "        \"sunset\": 1543761379\n" +
            "    },\n" +
            "    \"id\": 745044,\n" +
            "    \"name\": \"Istanbul\",\n" +
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

}