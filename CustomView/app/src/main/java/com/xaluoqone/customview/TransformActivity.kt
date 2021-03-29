package com.xaluoqone.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.xaluoqone.customview.widget.TransformView

class TransformActivity : AppCompatActivity() {
    private lateinit var changeCameraRotateX: SeekBar
    private lateinit var transformView: TransformView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transform)

        changeCameraRotateX = findViewById(R.id.changeCameraRotateX)
        transformView = findViewById(R.id.transformView)

        changeCameraRotateX.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                transformView.cameraRotateX = progress.toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }
}