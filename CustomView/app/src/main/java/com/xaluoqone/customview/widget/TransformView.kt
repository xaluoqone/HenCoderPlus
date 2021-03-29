package com.xaluoqone.customview.widget

import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withSave
import com.xaluoqone.customview.R
import com.xaluoqone.customview.ex.dp
import com.xaluoqone.customview.ex.getFitBitmap

class TransformView(context: Context, attrs: AttributeSet? = null) :
    View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val camera = Camera()
    var radius = 120.dp
        set(value) {
            field = value
            invalidate()
        }

    var cameraRotateX = 0f
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {
        val centerX = width / 2
        val centerY = height / 2
        canvas.withSave {
            canvas.translate(centerX.toFloat(), centerY.toFloat())
            camera.save()
            camera.setLocation(0f, 0f, -6 * resources.displayMetrics.density)
            camera.rotateX(cameraRotateX)
            camera.applyToCanvas(canvas)
            camera.restore()
            canvas.translate(-centerX.toFloat(), -centerY.toFloat())
            canvas.drawBitmap(
                context.getFitBitmap(R.mipmap.avatar, radius.toInt() * 2),
                centerX - radius,
                centerY - radius,
                paint
            )
        }
    }
}