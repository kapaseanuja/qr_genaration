package com.example.generate_qr_code

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.JobIntentService
import android.util.Log
import android.widget.Toast


class MyService : JobIntentService() {
    override fun onHandleWork(intent: Intent) {
        onHandleIntent(intent)
    }

    private fun onHandleIntent(intent: Any) {
        Log.i("MyTestService", "Service running");
//        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
//
//        val alarmIntent = Intent(this, MyAlarmReceiver::class.java)
//        val pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0)
//        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, 0, 5000, pendingIntent)

    }
}