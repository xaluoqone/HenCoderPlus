package com.xaluoqone.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SwitchCompat
import com.xaluoqone.customview.widget.MaterialEditText

class EditTextActivity : AppCompatActivity() {
    private lateinit var met: MaterialEditText
    private lateinit var switchMaterialEditTextShowTopHint: SwitchCompat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_text)

        met = findViewById(R.id.met)
        switchMaterialEditTextShowTopHint = findViewById(R.id.switchMaterialEditTextShowTopHint)

        switchMaterialEditTextShowTopHint.setOnCheckedChangeListener { _, isChecked ->
            met.showTopHint = isChecked
        }
    }
}