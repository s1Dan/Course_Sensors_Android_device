package com.s1dan.android_lab_9

import android.hardware.SensorManager
import android.hardware.SensorEvent
import android.text.method.ScrollingMovementMethod
import android.content.Context
import android.hardware.Sensor
import android.os.Bundle
import android.widget.TextView
import android.hardware.SensorEventListener
import androidx.appcompat.app.AppCompatActivity


class SecondActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private lateinit var mLight: Sensor
    private lateinit var myText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        myText = findViewById(R.id.myText)
        myText.setText("""
    Значение освещенности в люксах:
    
    """.trimIndent())
        myText.movementMethod = ScrollingMovementMethod()
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mLight = sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Действия, если точность изменилась
    }

    override fun onSensorChanged(event: SensorEvent) {
        val lux = event.values[0]
        myText!!.append("""
    ${java.lang.Float.toString(lux)}
    
    """.trimIndent())
    }

    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this, mLight,
            SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }
}