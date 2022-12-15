package com.example.generate_qr_code

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyAlarmReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent!!.action != null && intent!!.action.equals("android.intent.action.BOOT_COMPLETED")) {
            val serviceIntent = Intent(context, MyService::class.java)
            context!!.startService(serviceIntent)
        } else {
            Toast.makeText(context!!.applicationContext, "Hi codeplayon", Toast.LENGTH_LONG).show()
        }
    }
}