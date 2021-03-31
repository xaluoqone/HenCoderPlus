package com.xaluoqone.customview.widget

import android.animation.TypeEvaluator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import com.xaluoqone.customview.ex.dp

class PointView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 20.dp
        strokeCap = Paint.Cap.ROUND
        color = Color.BLUE
    }

    var point = PointF(0f, 0f)
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPoint(point.x, point.y, paint)
    }
}

class PointFEvaluator : TypeEvaluator<PointF> {
    override fun evaluate(fraction: Float, startValue: PointF, endValue: PointF): PointF {
        val pointX = startValue.x + (endValue.x - startValue.x) * fraction
        val pointY = startValue.y + (endValue.y - startValue.y) * fraction
        return PointF(pointX, pointY)
    }
}