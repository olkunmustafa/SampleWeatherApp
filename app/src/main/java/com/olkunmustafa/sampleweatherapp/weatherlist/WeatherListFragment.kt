package com.olkunmustafa.sampleweatherapp.weatherlist

import android.content.Context
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

class WeatherListFragment : BaseFragment(), IWeatherListContract.View {

    @Inject
    lateinit var presenter: WeatherListPresenter

    @BindView(R.id.weather_request_list)
    lateinit var weatherListRequestView: RecyclerView

    @BindView(R.id.no_empty_view_text)
    lateinit var noEmptyViewText: AppCompatTextView


    private lateinit var viewManager: RecyclerView.LayoutManager

    companion object {

        fun newInstance(): WeatherListFragment {
            val args = Bundle()
            val fragment = WeatherListFragment()

            fragment.arguments = args
            return fragment
        }

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        DaggerWeatherListComponent.builder()
            .appModule(AppModule(activity!!))
            .build()
            .inject(this)

        presenter.attached(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.presenter.setView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_weather_list, container, false)

        ButterKnife.bind(this, rootView)

        this.presenter.created()

        return rootView
    }

    override fun onStart() {
        super.onStart()
        presenter.started()
    }

    override fun onStop() {
        super.onStop()
        presenter.stopped()
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.destroyed()
    }

    override fun init() {
        viewManager = LinearLayoutManager(activity)
    }

    override fun showEmptyListView() {
        if (this.noEmptyViewText.visibility == View.GONE) {
            this.noEmptyViewText.visibility = View.VISIBLE
        }

        if (this.weatherListRequestView.visibility == View.VISIBLE) {
            this.weatherListRequestView.visibility = View.GONE
        }
    }

    override fun showWeatherList() {
        if (this.weatherListRequestView.visibility == View.GONE) {
            this.weatherListRequestView.visibility = View.VISIBLE
        }

        if (this.noEmptyViewText.visibility == View.VISIBLE) {
            this.noEmptyViewText.visibility = View.GONE
        }
    }

    override fun setAdapter(adapter: WeatherListAdapter) {
        this.weatherListRequestView.setHasFixedSize(true)
        this.weatherListRequestView.layoutManager = viewManager

        this.weatherListRequestView.adapter = adapter
    }

}