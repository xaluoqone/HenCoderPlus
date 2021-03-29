package com.xaluoqone.customview.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.util.AttributeSet
import android.view.View
import com.xaluoqone.customview.R
import com.xaluoqone.customview.ex.dp
import com.xaluoqone.customview.ex.getFitBitmap

class RoundAvatarView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    var bitmapId = R.mipmap.avatar
        set(value) {
            field = value
            invalidate()
        }
    var borderWidth = 5.dp
        set(value) {
            field = value
            invalidate()
        }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(width / 2f, height / 2f, width / 2f, paint)
        val saveCount = canvas.saveLayer(
            0f,
            height / 2f - width / 2,
            width.toFloat(),
            height / 2f + width / 2,
            null
        )
        canvas.drawCircle(width / 2f, height / 2f, width / 2f - borderWidth, paint)
        paint.xfermode = xfermode
        canvas.drawBitmap(
            context.getFitBitmap(bitmapId, (width - borderWidth * 2).toInt()),
            borderWidth,
            borderWidth,
            paint
        )
        paint.xfermode = null
        canvas.restoreToCount(saveCount)
    }
}