package com.xaluoqone.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.widget.AppCompatSeekBar
import com.xaluoqone.customview.ex.dp
import com.xaluoqone.customview.widget.RoundAvatarView

class MainActivity2 : AppCompatActivity() {
    private lateinit var avatar: RoundAvatarView
    private lateinit var avatarBorderWidthPlus: AppCompatSeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        avatar = findViewById(R.id.avatar)
        avatarBorderWidthPlus = findViewById(R.id.avatarBorderWidthPlus)

        avatarBorderWidthPlus.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                avatar.borderWidth = progress.dp
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }
}