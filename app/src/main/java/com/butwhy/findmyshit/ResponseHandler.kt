package com.butwhy.findmyshit

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.LocationManager
import android.location.Location
import android.util.Log
import androidx.core.app.ActivityCompat
import java.util.function.Consumer

class ResponseHandler(private val context: Context, private val lm: LocationManager) {
	private val location_criteria = Criteria()

  init {
		//preferable providers
		location_criteria.accuracy = Criteria.ACCURACY_FINE
		location_criteria.powerRequirement = Criteria.POWER_HIGH
		location_criteria.horizontalAccuracy = Criteria.ACCURACY_HIGH
		location_criteria.isCostAllowed = true
	}
	fun soundTheAlarms(replyAddr: String) {

	}
	private fun getLocation(): Array<Double>? {
		val res: Array<Double> = arrayOf(0.0, 0.0)
		if (lm.isLocationEnabled) {
			if (lm.allProviders.size != 0) {
				val provider = lm.getBestProvider(location_criteria, true) ?: return null
				//getBestProvider returns null if there is no provider available

				if (ActivityCompat.checkSelfPermission(context,	Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) 
				{
					// TODO: Consider calling
					//    ActivityCompat#requestPermissions
					// here to request the missing permissions, and then overriding
					//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
					//                                          int[] grantResults)
					// to handle the case where the user grants the permission. See the documentation
					// for ActivityCompat#requestPermissions for more details.
					return null
				} else {
					Log.d("main_log", "location is disabled, enabled it")
					//TODO: make a toast pop up
				}
				lm.getCurrentLocation(provider, null, context.mainExecutor) {
					res[0] = it.latitude
					res[1] = it.longitude
					Log.d("main_log", "check 1$res")
				}
				Log.d("main_log", "check 2$res")
				return res
			} 			
		}
		return null
	}
	fun sendLocation() {
		getLocation()
	}
}
