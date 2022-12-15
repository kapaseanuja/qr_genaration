package com.example.generate_qr_code

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.view.View
import android.widget.Toast
import com.example.generate_qr_code.databinding.ActivityMainBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import java.lang.String
import java.util.*
import kotlin.Long
import kotlin.toString


class MainActivity : AppCompatActivity(), View.OnClickListener{
    private lateinit var binding:ActivityMainBinding
    private val REQUEST_CODE = 100
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent
    val interval = 300000
    var counter = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.idBtnGenerateQR.setOnClickListener(this)
        binding.btnStartAlarm.setOnClickListener(this)
//        binding.btnCancelAlarm.setOnClickListener(this)

        // Creating the pending intent to send to the BroadcastReceiver
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, MyAlarmReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        val alarmIntent = Intent(this, MyAlarmReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0)


        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis(),
            interval.toLong(),
            pendingIntent
        )

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.idBtnGenerateQR ->{
                if(!binding.idEdt.text.isNullOrEmpty()){
                    var getStringValue= binding.idEdt.text
                    generateQrCode(getStringValue)
                }
            }

            R.id.btnStartAlarm ->{
                startAlarm()
            }

            R.id.btnCancelAlarm ->{
                cancelAlarm()
            }
        }
    }

    private fun startAlarm() {

        object : CountDownTimer(300000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.idTVPin.setText(String.valueOf(counter))
                counter++
            }

            override fun onFinish() {
                binding.idTVPin.setText("FINISH!!")
            }
        }.start()


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, 0, pendingIntent)
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            alarmManager.setExact(AlarmManager.RTC_WAKEUP, 0, pendingIntent)
//        } else {
//            alarmManager[AlarmManager.RTC_WAKEUP, 0] = pendingIntent
//        }
    }

    private fun cancelAlarm() {
        alarmManager.cancel(pendingIntent)
        Toast.makeText(applicationContext, "Alarm Cancelled", Toast.LENGTH_LONG).show()
    }

    private fun generateQrCode(stringValue: Editable?) {
        val writer = QRCodeWriter()
        val bitMatrix = writer.encode(stringValue.toString(), BarcodeFormat.QR_CODE, 512, 512)
        val width = bitMatrix.width
        val height = bitMatrix.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(x, y, if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
            }
        }
        binding.idIVQrcode.setImageBitmap(bitmap)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Cancels the pendingIntent if it is no longer needed after this activity is destroyed.
        alarmManager.cancel(pendingIntent)
    }


}