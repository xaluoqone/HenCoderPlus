package com.example.core.utils

import android.content.Context
import com.example.core.BaseApplication.Companion.currentApplication
import com.example.core.R

object CacheUtils {
    private val SP = currentApplication().getSharedPreferences(currentApplication().getString(R.string.app_name) , Context.MODE_PRIVATE)
    fun save(key : String? , value : String?) {
        SP.edit()
            .putString(key , value)
            .apply()
    }

    operator fun get(key : String?) : String? {
        return SP.getString(key , null)
    }
}