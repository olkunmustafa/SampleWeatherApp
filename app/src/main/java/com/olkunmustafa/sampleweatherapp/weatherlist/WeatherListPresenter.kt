package com.olkunmustafa.sampleweatherapp.weatherlist

import android.content.Context
import com.olkunmustafa.sampleweatherapp.data.storage.WeatherRequest
import com.olkunmustafa.sampleweatherapp.data.weatherlist.IWeatherUtil
import com.olkunmustafa.sampleweatherapp.weatherlist.adapter.WeatherListAdapter
import com.olkunmustafa.sampleweatherapp.weathermain.listener.IFragmentListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject
import org.greenrobot.eventbus.ThreadMode
import org.greenrobot.eventbus.Subscribe

open class WeatherListPresenter @Inject constructor() : IWeatherListContract.Presenter {

    private lateinit var dis1: Disposable
    private lateinit var dis2: Disposable
    private lateinit var mView: IWeatherListContract.View
    private  var iFragmentListener: IFragmentListener? = null

    @Inject
    lateinit var iWeatherListUtil: IWeatherUtil

    @Inject
    lateinit var weatherListAdapter: WeatherListAdapter

    override fun setView(view: IWeatherListContract.View) {
        this.mView = view
    }

    override fun attached(context: Context?) {
        if( context is IFragmentListener )
            this.iFragmentListener = context
    }

    override fun created() {
        this.mView.init()

        this.dis1 =
                this.iWeatherListUtil.getWeatherRequestList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { weatherList -> getWeatherListOnNext(weatherList) },
                        { error -> getWeatherListOnError(error) }
                    )

        this.dis2 = weatherListAdapter
            .clickSubject
            .subscribe {
                this.iFragmentListener?.openDetailFragment(it)
            }
    }

    override fun started() {
        EventBus.getDefault().register(this)
    }

    override fun stopped() {
        EventBus.getDefault().unregister(this)
    }

    override fun destroyed() {
        if (!dis1.isDisposed) {
            this.dis1.dispose()
        }

        if( !dis2.isDisposed ){
            this.dis2.dispose()
        }

        weatherListAdapter.destroy()
    }

    override fun getWeatherListOnNext(weatherList: List<WeatherRequest>) {
        if (weatherList.isEmpty()) {
            this.mView.showEmptyListView()
        } else {
            this.weatherListAdapter.weatherRequestList.clear()
            this.weatherListAdapter.weatherRequestList.addAll(weatherList)
            this.mView.showWeatherList()
            this.mView.setAdapter( this.weatherListAdapter )
        }
    }

    override fun getWeatherListOnError(throwable: Throwable) {
        throwable.printStackTrace()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(request: WeatherRequest) {
        this.weatherListAdapter.weatherRequestList.add(0,request)
        this.weatherListAdapter.notifyDataSetChanged()
    }
}