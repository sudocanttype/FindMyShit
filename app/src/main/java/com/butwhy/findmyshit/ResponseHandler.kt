package com.butwhy.findmyshit

import android.util.Log
import android.telephony.SmsManager
import android.location.LocationManager


class ResponseHandler {
	fun soundTheAlarms(replyAddr: String) {

	}
	fun getLocation() {
		if (LocationManager.isLocationEnabled) {

		} else {
			Log.d("main_log", "location is disabled, enabled it")
			//TODO: make a toast pop up
		}
	}
	fun sendLocation() {

	}
}
