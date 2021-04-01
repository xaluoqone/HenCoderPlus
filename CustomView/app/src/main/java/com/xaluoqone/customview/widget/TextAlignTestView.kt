package com.xaluoqone.customview.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.xaluoqone.customview.ex.sp

class TextAlignTestView(context: Context, attrs: AttributeSet? = null) :
    View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val fontMetrics = Paint.FontMetrics()
    private val fontMetrics2 = Paint.FontMetrics()
    private val bounds = Rect()
    var text = "我是一串文本信息......"

    override fun onDraw(canvas: Canvas) {
        paint.textSize = 20.sp
        paint.getFontMetrics(fontMetrics)
        canvas.drawText(text, 0f, -fontMetrics.top, paint)
        paint.textSize = 40.sp
        paint.getFontMetrics(fontMetrics2)
        paint.getTextBounds(text, 0, text.length, bounds)
        canvas.drawText(text, -bounds.left.toFloat(), -fontMetrics2.top - fontMetrics.top, paint)
    }
}