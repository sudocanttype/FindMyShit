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
        //whenever anything gets broadcasted this catches it
        if (intent.action.toString() == "android.provider.Telephony.SMS_RECEIVED") {
            val pdus = (intent.extras?.get("pdus")) as Array<*>
            //the important shit is in the extras and pdus
            for (kekw in pdus) {
                val sms = SmsMessage.createFromPdu(kekw as ByteArray?).messageBody
                //pull out that msg data
                if ( sms.count{ it == '-' } != 1 ){
                    return 
                }
                var (test, content) = sms.split("-")
                if (test == key) {
                    content = content.lowercase()
                    Log.d("SMS_Receiver", content)
                    //check what the message is and act accordingly
                    if ( content == "play" || content == "alarm" || content == "sound" || content == "ring"){
                        //play a tone/alarm for easy finding

                    } else if ( content == "location" || content == "locate" || content == "find" || content == "place"){
                        //send location preferably high accuracy

                    } else if (  content == "lost" || content == "lost mode" || content == "lostmode" || content == "fuck" ){
                        //lost mode -> begin logging locations every 3 minutes, send location every time screen is on

                    }
                }
            }
        }
    }
}

//todo:
//play alarm, send location, lost mode -> begin logging locations every 3 minutes, send location every time screen is on
//Intent.ACTION_SCREEN_ON
