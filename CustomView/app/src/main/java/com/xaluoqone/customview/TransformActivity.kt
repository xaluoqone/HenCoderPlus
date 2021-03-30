package com.xaluoqone.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.xaluoqone.customview.widget.TransformView

class TransformActivity : AppCompatActivity() {
    private lateinit var changeCameraTopRotateX: SeekBar
    private lateinit var changeCameraBottomRotateX: SeekBar
    private lateinit var changeCanvasBottomRotate: SeekBar
    private lateinit var transformView: TransformView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transform)

        changeCameraTopRotateX = findViewById(R.id.changeCameraTopRotateX)
        changeCameraBottomRotateX = findViewById(R.id.changeCameraBottomRotateX)
        changeCanvasBottomRotate = findViewById(R.id.changeCanvasBottomRotate)
        transformView = findViewById(R.id.transformView)

        changeCameraTopRotateX.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                transformView.cameraTopRotateX = progress.toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
        changeCameraBottomRotateX.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                transformView.cameraBottomRotateX = progress.toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
        changeCanvasBottomRotate.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                transformView.canvasBottomRotate = progress.toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }
}