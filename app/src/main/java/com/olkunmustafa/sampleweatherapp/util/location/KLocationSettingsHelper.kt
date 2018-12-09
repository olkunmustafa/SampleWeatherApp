package com.olkunmustafa.sampleweatherapp.util.location

import android.annotation.SuppressLint
import android.app.Activity
import android.location.Location
import com.google.android.gms.location.*
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import java.lang.RuntimeException


/**
 * Created by ismailgungor on 26.03.2018.
 *
 * TODO write comments for class responsibility. 13.04.2018
 */
class KLocationSettingsHelper(private var activity: Activity) {

	companion object {
		const val REQUEST_CHECK_SETTINGS = 9003
	}

	private var locationRequest: LocationRequest? = null
	private var locationSettingsRequest: LocationSettingsRequest? = null

	@SuppressLint("MissingPermission")
	fun createDefaultLocationRequest(): Flowable<Location> {
		return Flowable.create<Location>({ emitter ->
			this.locationRequest = LocationRequest.create()
			this.locationRequest?.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
			this.locationRequest?.interval = (30 * 1000).toLong()
			this.locationRequest?.fastestInterval = (5 * 1000).toLong()
			this.locationRequest?.let {
				this.locationSettingsRequest = LocationSettingsRequest.Builder().addLocationRequest(it).setAlwaysShow(true).build()
			}

			val mLocationCallback = object : LocationCallback() {
				override fun onLocationResult(locationResult: LocationResult?) {
					if (locationResult == null) {
						return
					}
					emitter.onNext( locationResult.locations[0] )
					emitter.onComplete()
				}

				override fun onLocationAvailability(availability: LocationAvailability?) {
					super.onLocationAvailability(availability)
					availability?.let {
						if( !it.isLocationAvailable )
							emitter.onError( RuntimeException() )
					}

				}
			}
			LocationServices.getFusedLocationProviderClient(activity)
				.requestLocationUpdates(locationRequest, mLocationCallback, null)


		}, BackpressureStrategy.BUFFER)

	}

}