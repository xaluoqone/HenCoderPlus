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
    var value = 0
        set(value) {
            field = value
            requestLayout()
            invalidate()
        }
    private val fontMetrics = Paint.FontMetrics()

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 20.dp
        textSize = 100.dp
        textAlign = Paint.Align.CENTER
    }

    override fun onDraw(canvas : Canvas) {
        super.onDraw(canvas)
        paint.style = Paint.Style.STROKE
        paint.color = Color.parseColor("#CCCCCC")
        canvas.drawCircle(width / 2f , height / 2f , 150.dp , paint)
        paint.color = Color.GREEN
        canvas.drawArc(width / 2f - radius , height / 2f - radius , width / 2f + radius , height / 2f + radius , -90f , if (value == 0) 0f else (value / 100f) * 360 , false , paint)
        paint.style = Paint.Style.FILL
        paint.color = Color.parseColor("#ff9588")
        paint.getFontMetrics(fontMetrics)
        //paint.getTextBounds()
        //文字居中：文字主要内容的(上边+下边)/2就是文字的垂直中心点（相对）
        canvas.drawText("$value%" , width / 2f , (height / 2f) - ((fontMetrics.ascent + fontMetrics.descent) / 2) , paint)
    }
}