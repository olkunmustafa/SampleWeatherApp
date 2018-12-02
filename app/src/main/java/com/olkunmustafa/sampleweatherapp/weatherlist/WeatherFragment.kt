package com.olkunmustafa.sampleweatherapp.weatherlist

import android.os.Bundle
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.olkunmustafa.sampleweatherapp.AppModule
import com.olkunmustafa.sampleweatherapp.BaseFragment
import com.olkunmustafa.sampleweatherapp.R
import com.olkunmustafa.sampleweatherapp.weatherlist.adapter.WeatherListAdapter
import javax.inject.Inject

class WeatherFragment : BaseFragment(), IWeatherListContract.View {

    @Inject
    lateinit var presenter: WeatherListPresenter

    @BindView(R.id.weather_request_list)
    lateinit var weatherListRequestView: RecyclerView

    @BindView(R.id.no_empty_view_text)
    lateinit var noEmptyViewText : AppCompatTextView

    private lateinit var viewManager: RecyclerView.LayoutManager

    companion object {

        fun newInstance(): WeatherFragment {
            val args = Bundle()
            val fragment = WeatherFragment()

            fragment.arguments = args
            return fragment
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerWeatherListComponent.builder()
            .appModule(AppModule(activity!!))
            .build()
            .inject(this)

        this.presenter.setView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_weather, container, false)

        ButterKnife.bind(this, rootView)

        this.presenter.created()

        return rootView
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.destroyed()
    }

    override fun init() {
        viewManager = LinearLayoutManager(activity)
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showEmptyListView() {
        if( this.noEmptyViewText.visibility == View.GONE )
            this.noEmptyViewText.visibility = View.VISIBLE
    }

    override fun setAdapter(adapter: WeatherListAdapter) {
        this.weatherListRequestView.setHasFixedSize(true)
        this.weatherListRequestView.layoutManager = viewManager

        this.weatherListRequestView.adapter = adapter
    }

}