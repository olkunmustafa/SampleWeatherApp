package com.olkunmustafa.sampleweatherapp.weatherdetail.util

import android.os.Bundle
import com.olkunmustafa.sampleweatherapp.util.Optional
import com.olkunmustafa.sampleweatherapp.weatherdetail.WeatherDetailConstants
import io.reactivex.Observable
import java.lang.IllegalArgumentException

class ArgumentUtil {

    fun checkArgsNotNull(args: Bundle?): Observable<Bundle> {

        return Observable.just( Optional(args) )
            .flatMap {optional ->
                if( optional.isEmpty ){
                    Observable.error<Bundle>(NoSuchElementException( "To show the neccessary elements Arguments should not be null!" ))
                } else {
                    Observable.just( optional.get() )
                }

            }
    }

    fun checkArgRecordContains(args: Bundle): Observable<Int> {

        return Observable.just( args )
            .flatMap { it ->
                if( it.containsKey( WeatherDetailConstants.RECORD_ID ) ){
                    val recordID = args.getInt(  WeatherDetailConstants.RECORD_ID )
                    if( recordID == 0 ){
                        Observable.error<Int>(IllegalArgumentException("In order to get data from local storage, the record id should not be equal to zero!"))
                    } else {
                        Observable.just(args.getInt(  WeatherDetailConstants.RECORD_ID ))
                    }
                } else {
                    Observable.error(NoSuchElementException( "To call the selected weather from local database, RecordID should not be null" ))
                }
            }

    }

}