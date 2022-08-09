package com.s1dan.android_lab_9

import android.app.ListActivity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorManager
import android.view.View
import android.widget.Button
import android.widget.ScrollView


class MainActivity : AppCompatActivity(), View.OnClickListener {
    var sensorManager: SensorManager? = null
    var sV: ScrollView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val detectorsBTN: Button = findViewById(R.id.button3)
        val lightBTN: Button = findViewById(R.id.light_btn)
        val accelBTN: Button = findViewById(R.id.accelerometer_btn)
        detectorsBTN.setOnClickListener(this)
        lightBTN.setOnClickListener(this)
        lightBTN.setOnClickListener {
            openSecondActivity()
        }
        accelBTN.setOnClickListener {
            openThirdActivity()
        }
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    private fun openSecondActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

    private fun openThirdActivity() {
        val intent = Intent(this, ThirdActivity::class.java)
        startActivity(intent)
    }

    override fun onClick(view: View) {
        val myText = findViewById<TextView>(R.id.myText)
        when (view.id) {
            R.id.button3 -> {
                myText.text = ""
                if (sensorManager!!.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null) {

                    myText.append("На устройстве есть гироскоп\n")

                } else {

                    myText.append("На устройстве нет гироскопа\n")
                }

                if (sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {

                    myText.append("На устройстве есть акселерометр\n")

                } else {

                    myText.append("На устройстве нет акселерометра\n")
                }

                if (sensorManager!!.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION) != null) {

                    myText.append("""
    На устройстве есть датчик
    обнаружения значимого движения для пробуждения устройства
    
    """.trimIndent())

                } else {

                    myText.append("""
    На устройстве нет датчика
    обнаружения значимого движения для пробуждения устройства
    
    """.trimIndent())
                }

                if (sensorManager!!.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) != null) {

                    myText.append("На устройстве есть датчик отслеживания шагов\n")

                } else {

                    myText.append("На устройстве нет  датчика отслеживания шагов\n")
                }

                if (sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT) != null) {

                    myText.append("На устройстве есть датчик света\n")

                } else {

                    myText.append("На устройстве нет датчика света\n")
                }

                if (sensorManager!!.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null) {

                    myText.append("На устройстве есть датчик магнитного поля\n")

                } else {

                    myText.append("На устройстве нет датчика магнитного поля\n")
                }

                myText.movementMethod = ScrollingMovementMethod()
            }
        }
    }
}