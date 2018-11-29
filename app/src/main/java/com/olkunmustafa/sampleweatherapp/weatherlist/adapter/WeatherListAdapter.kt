package com.olkunmustafa.sampleweatherapp.weatherlist.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.olkunmustafa.sampleweatherapp.data.storage.WeatherRequest

class WeatherListAdapter(var mContext : Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var weatherRequestList : List<WeatherRequest>

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        return 0
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
    }
}