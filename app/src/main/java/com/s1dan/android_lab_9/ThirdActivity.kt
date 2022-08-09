package com.s1dan.android_lab_9

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import android.hardware.SensorEventListener
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class ThirdActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var myText: TextView
    private lateinit var sensorManager: SensorManager
    private lateinit var sensorAccel: Sensor
    private var valuesAccel = FloatArray(3)
    private lateinit var timer: Timer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        myText = findViewById(R.id.myText)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensorAccel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, sensorAccel,
            SensorManager.SENSOR_DELAY_NORMAL)
        timer = Timer()
        val task: TimerTask = object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    myText.text = ""
                    myText.append("""
    Ускорение
    X: ${valuesAccel[0]}
    Y: ${valuesAccel[1]}
    Z: ${valuesAccel[2]}
    """.trimIndent())
                }
            }
        }
        timer.schedule(task, 0, 1000)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
        timer.cancel()
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
    override fun onSensorChanged(event: SensorEvent) {
        for (i in 0..2) {
            valuesAccel[i] = event.values[i]
        }
    }
}