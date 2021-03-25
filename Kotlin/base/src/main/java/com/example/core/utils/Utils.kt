package com.example.core.utils

import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast
import com.example.core.BaseApplication.Companion.currentApplication

val Number.dp
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics)


object Utils {
    fun toast(string: String?, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(currentApplication, string, duration)
                .show()
    }
}