package com.olkunmustafa.sampleweatherapp.weatherlist

import com.olkunmustafa.sampleweatherapp.data.storage.WeatherRequest
import com.olkunmustafa.sampleweatherapp.data.weatherlist.IWeatherListUtil
import com.olkunmustafa.sampleweatherapp.weatherlist.adapter.WeatherListAdapter
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.function.Consumer
import javax.inject.Inject

class WeatherListPresenter @Inject constructor() : IWeatherListContract.Presenter {

    lateinit var weatherListDisposable: Disposable
    lateinit var mView: IWeatherListContract.View

    @Inject
    lateinit var iWeatherListUtil: IWeatherListUtil

    @Inject
    lateinit var weatherListAdapter: WeatherListAdapter

    override fun setView(view: IWeatherListContract.View) {
        this.mView = view
    }

    override fun created() {
        this.weatherListDisposable =
                this.iWeatherListUtil.getWeatherRequestList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { this.getWeatherListOnNext() },
                        { this.getWeatherListOnError() })

    }

    override fun destroyed() {
        if (!weatherListDisposable.isDisposed) {
            this.weatherListDisposable.dispose()
        }
    }

    override fun getWeatherListOnNext(): Consumer<List<WeatherRequest>> {
        return Consumer { weatherList ->
            if (weatherList.isNotEmpty()) {
                weatherListAdapter.weatherRequestList = weatherList
            }
        }
    }

    override fun getWeatherListOnError(): Consumer<Throwable> {
        return Consumer { error ->
            error.printStackTrace()
        }
    }
}