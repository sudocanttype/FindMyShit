package com.butwhy.findmyshit

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.LocationManager
import android.os.BatteryManager
import android.telephony.SmsManager
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat

class ResponseHandler(private val context: Context, private val lm: LocationManager, private val sender: String) {
	private val location_criteria = Criteria()

  init {
		//preferable providers
		location_criteria.accuracy = Criteria.ACCURACY_FINE
		location_criteria.powerRequirement = Criteria.POWER_HIGH
		location_criteria.horizontalAccuracy = Criteria.ACCURACY_HIGH
		location_criteria.isCostAllowed = true
		Log.d("main", "handler init")
	}
	fun soundTheAlarms(replyAddr: String) {

	}
	fun textString(content:String) {
		val sm = SmsManager.getDefault()
		sm.sendTextMessage(sender, null, content, null, null)
	}
	fun sendLocation() {
		val res: Array<Double> = arrayOf(0.0, 0.0)
		Log.d("main", "locate activated")
		if (lm.isLocationEnabled) {
			if (lm.allProviders.size != 0) {
				val provider = lm.getBestProvider(location_criteria, true) ?: return
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
					// Toast.makeText(context, "Location is disabled, enable it.", Toast.LENGTH_LONG).show()
					Toast.makeText(context, "Insufficient permissions, please grant FindMyShit the necessary permissions.", Toast.LENGTH_LONG).show()
					textString("Not all permissions granted, location access failed.")
					return
				}
					lm.getCurrentLocation(provider, null, context.mainExecutor) {
					res[0] = it.latitude
					res[1] = it.longitude
					//do everything here because this call is async
					Log.d("main_log", "check 1 "+ res[0] + " "+res[1])
					textString("location is at "+ res[0] + ", "+res[1])
				}
			} 			
		}
	}
	fun enableLostMode(){
		Log.d("main_log", "lost mode enabled")
		//TODO:enable battery saver - jk that just doesnt exist
		//return current location as well as battery info
		sendLocation()
		val bm = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
		val batLevel = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
		Log.d("main_log", "battery is currently " + batLevel.toString())
	}
}
