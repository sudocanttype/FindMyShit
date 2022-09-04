package com.butwhy.findmyshit
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.telephony.SmsMessage
import android.util.Log


class SMS_Receiver : BroadcastReceiver() {
    private var key: String = "423kslf"
        get() {
            return field
        }
        set(name) {
            field = name
        }

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action.toString() == "android.provider.Telephony.SMS_RECEIVED") {
            val pdus = (intent.extras?.get("pdus")) as Array<*>
            for (kekw in pdus) {
                val sms = SmsMessage.createFromPdu(kekw as ByteArray?).messageBody
                if ( sms.count{ it == '-' } != 1 ){
                    return 
                }
                val (test, content) = sms.split("-")
                if (test == key) {
                    Log.d("SMS_Receiver", content)
                }
        }

            // Will do stuff with message here
        }
    }
}

