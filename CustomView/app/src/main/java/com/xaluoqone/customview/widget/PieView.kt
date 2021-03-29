package com.xaluoqone.customview.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.xaluoqone.customview.ex.dp
import kotlin.math.cos
import kotlin.math.sin

class PieView(context: Context, attrs: AttributeSet? = null) :
    View(context, attrs) {

    private val pieAngles = floatArrayOf(30f, 120f, 50f, 70f, 90f)
    private val pieColors =
        arrayListOf(
            Color.parseColor("#673AB7"),
            Color.parseColor("#2196F3"),
            Color.parseColor("#00BCD4"),
            Color.parseColor("#009688"),
            Color.parseColor("#FF5722")
        )

    var currentPieIndex = 4
        set(value) {
            field = if (value > pieAngles.size - 1) 0 else value
            invalidate()
        }

    var pieRadius = 120.dp
        set(value) {
            field = value
            invalidate()
        }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas) {
        val centerX = width / 2
        val centerY = height / 2
        var startAngle = 0f
        pieAngles.forEachIndexed { index, pieAngle ->
            paint.color = pieColors[index]
            if (currentPieIndex == index) {
                canvas.save()
                canvas.translate(
                    cos(Math.toRadians(startAngle + pieAngle / 2.0).toFloat()) * 15.dp,
                    sin(Math.toRadians(startAngle + pieAngle / 2.0).toFloat()) * 15.dp
                )
            }
            canvas.drawArc(
                centerX - pieRadius,
                centerY - pieRadius,
                centerX + pieRadius,
                centerY + pieRadius,
                startAngle,
                pieAngle,
                true,
                paint
            )
            if (currentPieIndex == index) {
                canvas.restore()
            }
            startAngle += pieAngle
        }
    }
}