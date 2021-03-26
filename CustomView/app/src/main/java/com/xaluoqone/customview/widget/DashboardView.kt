package com.xaluoqone.customview.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.xaluoqone.customview.ex.dp
import kotlin.math.cos
import kotlin.math.sin

class DashboardView(context: Context, attrs: AttributeSet? = null) :
    View(context, attrs) {
    var openAngle = 120f
        set(value) {
            field = value
            requestLayout()
            invalidate()
        }
    var radius = 120.dp
        set(value) {
            field = value
            requestLayout()
            invalidate()
        }
    var scaleCount = 20
        set(value) {
            field = value
            requestLayout()
            invalidate()
        }
    var speed = 6
        set(value) {
            field = if (value > scaleCount) 0 else value
            requestLayout()
            invalidate()
        }

    private val path = Path()
    private val dash = Path().apply {
        //实际伸出长度为Rect长度减去paint.strokeWidth/2
        addRect(0f, 0f, 3.dp, 9.dp, android.graphics.Path.Direction.CCW)
    }
    private lateinit var pathEffect: PathDashPathEffect

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#045FB4")
        style = Paint.Style.STROKE
        strokeWidth = 5.dp
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val centerX = width / 2f
        val centerY = height / 2f
        val startAngle = openAngle / 2 + 90
        val sweepAngle = 360 - openAngle
        path.reset()
        path.addArc(
            centerX - radius, centerY - radius, centerX + radius, centerY + radius,
            startAngle,
            sweepAngle
        )
        val pathMeasure = PathMeasure(path, false)
        pathEffect =
            PathDashPathEffect(
                dash,
                (pathMeasure.length - 3.dp) / scaleCount,
                0f,
                PathDashPathEffect.Style.ROTATE
            )

    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(path, paint)

        paint.pathEffect = pathEffect
        canvas.drawPath(path, paint)
        paint.pathEffect = null

        val centerX = width / 2f
        val centerY = height / 2f

        val pointerRadius =
            Math.toRadians((((360.0 - openAngle) / scaleCount) * speed) + (openAngle / 2 + 90))
                .toFloat()

        //原点坐标为x,y，则x1=x+距离*cos角度，y1=y+距离*sin角度
        canvas.drawLine(
            centerX,
            centerY,
            centerX + 90.dp * cos(pointerRadius),
            centerY + 90.dp * sin(pointerRadius),
            paint
        )
    }
}