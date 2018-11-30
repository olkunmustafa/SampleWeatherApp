package com.olkunmustafa.sampleweatherapp.weatherlist.adapter

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.google.gson.Gson
import com.olkunmustafa.sampleweatherapp.R
import com.olkunmustafa.sampleweatherapp.data.storage.WeatherRequest

class WeatherListAdapter(
    private var mContext: Context,
    private var mGson : Gson?
    ) : RecyclerView.Adapter<WeatherListAdapter.CardViewHolder>() {

    lateinit var weatherRequestList: List<WeatherRequest>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val rootView: View = LayoutInflater.from(mContext)
            .inflate(R.layout.item_weather_list, parent, false)

        return CardViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return this.weatherRequestList.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, indis: Int) {

        val weatherRequest : WeatherRequest = this.weatherRequestList[indis]
        holder.requestTime.text = weatherRequest.requestTime.toString()
        holder.temperature.text = weatherRequest.weatherdata.main.temp.toString()
        holder.location.text = weatherRequest.weatherdata.name

    }


    class CardViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        @BindView(R.id.request_time)
        lateinit var requestTime: AppCompatTextView

        @BindView(R.id.temperature)
        lateinit var temperature: AppCompatTextView

        @BindView(R.id.location)
        lateinit var location: AppCompatTextView

        init {
            ButterKnife.bind(this, view)
        }

    }
}