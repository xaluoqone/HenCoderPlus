package com.xaluoqone.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.SeekBar
import androidx.appcompat.widget.AppCompatSeekBar
import com.xaluoqone.customview.ex.dp
import com.xaluoqone.customview.widget.RoundAvatarView
import com.xaluoqone.customview.widget.TestView

class MainActivity2 : AppCompatActivity() {
    private lateinit var avatar: RoundAvatarView
    private lateinit var avatarBorderWidthPlus: AppCompatSeekBar
    private lateinit var testView: TestView
    private lateinit var nextMode: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        avatar = findViewById(R.id.avatar)
        avatarBorderWidthPlus = findViewById(R.id.avatarBorderWidthPlus)
        testView = findViewById(R.id.testView)
        nextMode = findViewById(R.id.nextMode)

        avatarBorderWidthPlus.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                avatar.borderWidth = progress.dp
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        nextMode.setOnClickListener {
            testView.porterDuffModeNumber++
            Log.e("当前模式", "${testView.porterDuffModeNumber}")
        }
    }
}