package com.xaluoqone.customview.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.xaluoqone.customview.ex.dp

class SportView(context : Context , attrs : AttributeSet? = null) : View(context , attrs) {
    private val radius = 150.dp
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeCap = Paint.Cap.ROUND
        style = Paint.Style.STROKE
        strokeWidth = 20.dp
        textSize = 100.dp
    }

    override fun onDraw(canvas : Canvas) {
        super.onDraw(canvas)
        paint.color = Color.parseColor("#CCCCCC")
        canvas.drawCircle(width / 2f , height / 2f , 150.dp , paint)
        paint.color = Color.GREEN
        canvas.drawArc(width / 2f - radius , height / 2f - radius , width / 2f + radius , height / 2f + radius , -90f , 180f , false , paint)
    }
}