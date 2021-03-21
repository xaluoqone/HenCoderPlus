package com.example.core.utils

import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast
import com.example.core.BaseApplication.Companion.currentApplication

fun dp2px(dp : Float) : Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP , dp , Resources.getSystem().displayMetrics)
}

object Utils {
    fun toast(string : String? , duration : Int = Toast.LENGTH_SHORT) {
        Toast.makeText(currentApplication() , string , duration)
            .show()
    }
}