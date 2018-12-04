package com.olkunmustafa.sampleweatherapp.weatherdetail.util

import android.os.Bundle
import com.olkunmustafa.sampleweatherapp.util.Optional
import com.olkunmustafa.sampleweatherapp.weatherdetail.WeatherDetailConstants
import io.reactivex.Observable

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
            .flatMap { args ->
                if( args.containsKey( WeatherDetailConstants.RECORD_ID ) ){
                    Observable.just(args.getInt(  WeatherDetailConstants.RECORD_ID ))
                } else {
                    Observable.error(NoSuchElementException( "To call the selected weather from local database, RecordID should not be null" ))
                }
            }

    }

}