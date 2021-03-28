package com.xaluoqone.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.xaluoqone.customview.ex.dp
import com.xaluoqone.customview.widget.RadarView

class RadarActivity : AppCompatActivity() {
    private lateinit var radar : RadarView
    private lateinit var changeRadius : SeekBar
    private lateinit var changeSideCount : SeekBar
    private lateinit var changeCircleCount : SeekBar

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_radar)

        radar = findViewById(R.id.radar)
        changeRadius = findViewById(R.id.changeRadius)
        changeSideCount = findViewById(R.id.changeSideCount)
        changeCircleCount = findViewById(R.id.changeCircleCount)

        changeRadius.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0 : SeekBar? , p1 : Int , p2 : Boolean) {
                radar.radius = p1.dp
            }

            override fun onStartTrackingTouch(p0 : SeekBar?) {

            }

            override fun onStopTrackingTouch(p0 : SeekBar?) {

            }
        })

        changeSideCount.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0 : SeekBar? , p1 : Int , p2 : Boolean) {
                radar.sideCount = if (p1 < 3) 3 else p1
            }

            override fun onStartTrackingTouch(p0 : SeekBar?) {

            }

            override fun onStopTrackingTouch(p0 : SeekBar?) {

            }
        })

        changeCircleCount.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0 : SeekBar? , p1 : Int , p2 : Boolean) {
                radar.circleCount = if (p1 < 3) 3 else p1
            }

            override fun onStartTrackingTouch(p0 : SeekBar?) {

            }

            override fun onStopTrackingTouch(p0 : SeekBar?) {

            }
        })
    }
}