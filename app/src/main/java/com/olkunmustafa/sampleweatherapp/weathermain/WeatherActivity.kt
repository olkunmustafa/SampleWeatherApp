package com.olkunmustafa.sampleweatherapp.weathermain

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.Nullable
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import com.olkunmustafa.sampleweatherapp.AppModule
import com.olkunmustafa.sampleweatherapp.R
import com.olkunmustafa.sampleweatherapp.weatherdetail.WeatherDetailFragment
import com.olkunmustafa.sampleweatherapp.weatherlist.WeatherListFragment
import com.olkunmustafa.sampleweatherapp.weathermain.listener.IFragmentListener
import javax.inject.Inject

class WeatherActivity : AppCompatActivity(), IWeatherContract.View, IFragmentListener {

    @Inject
    lateinit var presenter: WeatherPresenter

    @BindView(R.id.indeterminateBar)
    lateinit var indeterminateBar: ProgressBar

    @Nullable
    @JvmField
    @BindView(R.id.container)
    var container: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerWeatherComponent.builder()
            .appModule(AppModule(this))
            .weatherModule(WeatherModule(this))
            .build()
            .inject(this)

        ButterKnife.bind(this)
        this.presenter.setView(this)
        this.presenter.created()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.send_new_request -> {
                presenter.saveButtonClicked()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun replaceFragment() {
        container?.let {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, WeatherListFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun openDetailFragment(dataID: Int) {
        val detailFrag =
            supportFragmentManager.findFragmentById(R.id.weather_detail_fragment) as WeatherDetailFragment?

        if (detailFrag == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, WeatherDetailFragment.newInstance(dataID))
                .addToBackStack(null)
                .commit()

        } else {
            detailFrag.updateInterface(dataID)

        }

    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            super.onBackPressed()

        } else {
            this.finish()

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        presenter.requestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun showLoading() {
        if (this.indeterminateBar.visibility == View.GONE)
            this.indeterminateBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        if (this.indeterminateBar.visibility == View.VISIBLE)
            this.indeterminateBar.visibility = View.GONE
    }

}
