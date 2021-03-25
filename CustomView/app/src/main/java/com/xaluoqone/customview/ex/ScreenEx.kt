package com.xaluoqone.customview.ex

import android.content.res.Resources
import android.util.TypedValue

val Number.dp: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    )