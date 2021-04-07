package com.xaluoqone.customview.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.xaluoqone.customview.R
import com.xaluoqone.customview.ex.dp
import com.xaluoqone.customview.ex.getFitBitmap

class ScalableImageView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = context.getFitBitmap(R.mipmap.mz, 300.dp.toInt())

    override fun onDraw(canvas: Canvas) {
        val centerX = width / 2
        val centerY = height / 2
        canvas.drawBitmap(bitmap, centerX - bitmap.width / 2f, centerY - bitmap.height / 2f, paint)
    }
}