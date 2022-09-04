package com.butwhy.findmyshit
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.telephony.SmsMessage
import android.util.Log


class SMS_Receiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action.toString() == "android.provider.Telephony.SMS_RECEIVED") {
            Log.d("SMS_Receiver", "SMS received")
            val pdus = (intent.extras?.get("pdus")) as Array<*>
            for(kekw in pdus){

            }

            // Will do stuff with message here
        }
    }
}

