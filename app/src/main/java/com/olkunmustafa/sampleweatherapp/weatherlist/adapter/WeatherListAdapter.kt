package com.olkunmustafa.sampleweatherapp.weatherlist.adapter

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.olkunmustafa.sampleweatherapp.R
import com.olkunmustafa.sampleweatherapp.data.storage.WeatherRequest
import com.olkunmustafa.sampleweatherapp.data.util.createmodel.ICreateWeatherModel
import com.olkunmustafa.sampleweatherapp.data.util.dateutil.IDateUtil
import com.olkunmustafa.sampleweatherapp.data.util.iconutil.IIconUtil
import com.olkunmustafa.sampleweatherapp.data.util.temperatureutil.ITemperatureUtil
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

open class WeatherListAdapter(
    private val context: Context,
    private val icreateWeatherModel: ICreateWeatherModel,
    private val iDateUtil: IDateUtil,
    private val iIconUtil: IIconUtil,
    private val iTemperatureUtil: ITemperatureUtil
) : RecyclerView.Adapter<WeatherListAdapter.CardViewHolder>() {

    val weatherRequestList: ArrayList<WeatherRequest> = ArrayList()
    val clickSubject = PublishSubject.create<Int>()!!

    private var convertWeatherModelDis: Disposable? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val rootView: View = LayoutInflater.from(context)
            .inflate(R.layout.item_weather_list, parent, false)

        return CardViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return this.weatherRequestList.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, indis: Int) {
        val weatherRequest: WeatherRequest = this.weatherRequestList[indis]
        convertWeatherModelDis = icreateWeatherModel.convertWeatherModel(weatherRequest.weatherdata)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { weather ->
                holder.requestTime.text = iDateUtil.formatDate(weatherRequest.requestTime)
                holder.currentTemperature.text = iTemperatureUtil.getStyledTemperature( weather.main.temp )
                holder.currentMinMax.text = iTemperatureUtil.getStyledMinMaxTemperature( weather.main.tempMin, weather.main.tempMax )
                holder.location.text = weather.name
                holder.description.text = weather.weather[0].description

                weather.weather?.get(0)?.icon?.let {
                    Picasso
                        .with(this.context)
                        .load(iIconUtil.getIconFullUrl(it))
                        .into(holder.temperatureIcon)
                }

                holder.mainCardWrapper.setOnClickListener {
                    clickSubject.onNext(weatherRequest.uid)
                }
            }

    }

    fun destroy() {
        convertWeatherModelDis?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

    class CardViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        @BindView(R.id.main_card_wrapper)
        lateinit var mainCardWrapper : CardView

        @BindView(R.id.current_temperature)
        lateinit var currentTemperature : AppCompatTextView

        @BindView(R.id.current_min_max)
        lateinit var currentMinMax : AppCompatTextView

        @BindView(R.id.request_time)
        lateinit var requestTime: AppCompatTextView

        @BindView(R.id.location)
        lateinit var location: AppCompatTextView

        @BindView(R.id.temperature_icon)
        lateinit var temperatureIcon: AppCompatImageView

        @BindView(R.id.description)
        lateinit var description : AppCompatTextView

        init {
            ButterKnife.bind(this, view)
        }

    }
}