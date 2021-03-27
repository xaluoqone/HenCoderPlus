package com.xaluoqone.customview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.xaluoqone.customview.widget.DashboardView
import com.xaluoqone.customview.widget.PieView

class MainActivity : AppCompatActivity() {
    private lateinit var dashboardView: DashboardView
    private lateinit var speedPlus: Button
    private lateinit var pieView: PieView
    private lateinit var pieSwitch: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dashboardView = findViewById(R.id.dashboard)
        speedPlus = findViewById(R.id.speedPlus)
        pieView = findViewById(R.id.pieView)
        pieSwitch = findViewById(R.id.pieSwitch)

        pieView.setOnClickListener {
            startActivity(Intent(this@MainActivity, MainActivity2::class.java))
        }

        speedPlus.setOnClickListener {
            dashboardView.speed++
        }

        pieSwitch.setOnClickListener {
            pieView.currentPieIndex++
        }
    }
}