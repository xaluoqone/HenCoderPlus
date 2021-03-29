package com.xaluoqone.customview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var intoDashboardAndPie : Button
    private lateinit var intoRadar : Button
    private lateinit var intoPorterDuffXfermodeTest : Button
    private lateinit var intoSportActivity : Button

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intoDashboardAndPie = findViewById(R.id.intoDashboardAndPie)
        intoRadar = findViewById(R.id.intoRadar)
        intoPorterDuffXfermodeTest = findViewById(R.id.intoPorterDuffXfermodeTest)
        intoSportActivity = findViewById(R.id.intoSportActivity)

        intoDashboardAndPie.setOnClickListener {
            startActivity(Intent(this@MainActivity , DashboardAndPieActivity::class.java))
        }

        intoRadar.setOnClickListener {
            startActivity(Intent(this@MainActivity , RadarActivity::class.java))
        }

        intoPorterDuffXfermodeTest.setOnClickListener {
            startActivity(Intent(this@MainActivity , PorterDuffXfermodeTestActivity::class.java))
        }

        intoSportActivity.setOnClickListener {
            startActivity(Intent(this@MainActivity , SportActivity::class.java))
        }
    }
}