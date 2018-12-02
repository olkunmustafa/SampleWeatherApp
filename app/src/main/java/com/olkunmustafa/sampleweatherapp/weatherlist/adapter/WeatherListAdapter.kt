package com.olkunmustafa.sampleweatherapp.weatherlist.adapter

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.olkunmustafa.sampleweatherapp.R
import com.olkunmustafa.sampleweatherapp.data.storage.WeatherRequest
import com.olkunmustafa.sampleweatherapp.data.util.createmodel.ICreateWeatherModel
import com.olkunmustafa.sampleweatherapp.data.util.dateutil.IDateUtil
import com.olkunmustafa.sampleweatherapp.data.util.iconutil.IIconUtil
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

open class WeatherListAdapter(
    private val context: Context,
    private val icreateWeatherModel: ICreateWeatherModel,
    private val iDateUtil: IDateUtil,
    private val iIconUtil: IIconUtil
) : RecyclerView.Adapter<WeatherListAdapter.CardViewHolder>() {

    lateinit var weatherRequestList: List<WeatherRequest>
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
                holder.requestTime.text = iDateUtil.formatDate(weatherRequest.requestTime!!)
                holder.temperature.text = weather.main.temp.toString()
                holder.location.text = weather.name

                weather.weather?.get(0)?.icon?.let {
                    Picasso
                        .with(this.context)
                        .load(iIconUtil.getIconFullUrl(it))
                        .into(holder.temperatureIcon)
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

        @BindView(R.id.request_time)
        lateinit var requestTime: AppCompatTextView

        @BindView(R.id.temperature)
        lateinit var temperature: AppCompatTextView

        @BindView(R.id.location)
        lateinit var location: AppCompatTextView

        @BindView(R.id.temperature_icon)
        lateinit var temperatureIcon: AppCompatImageView

        init {
            ButterKnife.bind(this, view)
        }

    }
}