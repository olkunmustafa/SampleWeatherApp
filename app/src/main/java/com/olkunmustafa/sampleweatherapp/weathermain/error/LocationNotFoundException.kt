package com.olkunmustafa.sampleweatherapp.weathermain.error

class LocationNotFoundException : RuntimeException {

    constructor() : super("Requested location can not found. Please be sure that device location is open.")

}