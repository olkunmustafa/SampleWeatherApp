package com.olkunmustafa.sampleweatherapp.weatherdetail

import android.os.Bundle
import com.olkunmustafa.sampleweatherapp.data.util.createmodel.ICreateWeatherModel
import com.olkunmustafa.sampleweatherapp.data.weatherlist.IWeatherUtil
import com.olkunmustafa.sampleweatherapp.weatherdetail.util.ArgumentUtil
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherDetailPresenter @Inject constructor() : IWeatherDetailContract.Presenter {

    @Inject
    lateinit var argumentUtil : ArgumentUtil

    @Inject
    lateinit var iweatherUtil: IWeatherUtil

    @Inject
    lateinit var iCreateWeatherModel: ICreateWeatherModel

    override fun setView(view: IWeatherDetailContract.View) {
    }

    override fun created(args: Bundle?) {

        this.argumentUtil
            .checkArgsNotNull(args)
            .subscribeOn( Schedulers.io() )
            .flatMap {bundle ->
                this.argumentUtil.checkArgRecordContains( bundle )
                    .flatMap { recordID ->
                        this.iweatherUtil.getWeather(recordID)
                    }

            }
            .flatMap {
                this.iCreateWeatherModel.convertWeatherModel( it.weatherdata )
            }

    }

}