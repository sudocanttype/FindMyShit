package com.butwhy.findmyshit

import android.content.Context
import android.location.Criteria
import android.location.LocationManager
import android.util.Log


class ResponseHandler(private val lm: LocationManager) {
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
	private fun getLocation() {
		if (lm.isLocationEnabled) {
			if (lm.allProviders.size != 0){

			}

		} else {
			Log.d("main_log", "location is disabled, enabled it")
			//TODO: make a toast pop up
		}
	}
	fun sendLocation() {

	}
}
