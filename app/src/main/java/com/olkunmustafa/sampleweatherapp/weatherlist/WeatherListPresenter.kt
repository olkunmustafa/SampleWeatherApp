package com.olkunmustafa.sampleweatherapp.weatherlist

import com.olkunmustafa.sampleweatherapp.data.storage.WeatherRequest
import com.olkunmustafa.sampleweatherapp.data.weatherlist.IWeatherListUtil
import com.olkunmustafa.sampleweatherapp.weatherlist.adapter.WeatherListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

open class WeatherListPresenter @Inject constructor() : IWeatherListContract.Presenter {

    private lateinit var weatherListDisposable: Disposable
    private lateinit var mView: IWeatherListContract.View

    @Inject
    lateinit var iWeatherListUtil: IWeatherListUtil

    @Inject
    lateinit var weatherListAdapter: WeatherListAdapter

    override fun setView(view: IWeatherListContract.View) {
        this.mView = view
    }

    override fun created() {
        this.mView.init()

        this.weatherListDisposable =
                this.iWeatherListUtil.getWeatherRequestList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { weatherList -> getWeatherListOnNext(weatherList) },
                        { error -> getWeatherListOnError(error) }
                    )

    }

    override fun destroyed() {
        if (!weatherListDisposable.isDisposed) {
            this.weatherListDisposable.dispose()
        }

        weatherListAdapter.destroy()
    }

    override fun getWeatherListOnNext(weatherList: List<WeatherRequest>) {
        if (weatherList.isEmpty()) {
            this.mView.showEmptyListView()
        } else {
            this.weatherListAdapter.weatherRequestList = weatherList
            this.mView.showAdapter()
            this.mView.setAdapter( this.weatherListAdapter )
        }
    }

    override fun getWeatherListOnError(throwable: Throwable) {
        throwable.printStackTrace()
    }
}