package com.xaluoqone.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.xaluoqone.customview.widget.SportView

class SportActivity : AppCompatActivity() {
    private lateinit var sportView : SportView
    private lateinit var changeSportProgress : SeekBar

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sport)

        sportView = findViewById(R.id.sportView)
        changeSportProgress = findViewById(R.id.changeSportProgress)

        changeSportProgress.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0 : SeekBar? , p1 : Int , p2 : Boolean) {
                sportView.value = p1
            }

            override fun onStartTrackingTouch(p0 : SeekBar?) {

            }

            override fun onStopTrackingTouch(p0 : SeekBar?) {

            }
        })
    }
}