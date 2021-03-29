package com.xaluoqone.customview.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.xaluoqone.customview.ex.dp

/**
 * 本View只用于测试xfermode的17个PorterDuff.Mode
 */
class TestView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val circleBitmap =
        Bitmap.createBitmap(180.dp.toInt(), 180.dp.toInt(), Bitmap.Config.ARGB_8888)
    private val rectBitmap =
        Bitmap.createBitmap(180.dp.toInt(), 180.dp.toInt(), Bitmap.Config.ARGB_8888)
    private var xfermode: PorterDuffXfermode
    var porterDuffModeNumber = 0
        set(value) {
            field = if (value > 17) 0 else value
            xfermode = PorterDuffXfermode(getPorterDuffModeNumber(porterDuffModeNumber))
            invalidate()
        }

    init {
        xfermode = PorterDuffXfermode(getPorterDuffModeNumber(porterDuffModeNumber))
        val circleCanvas = Canvas(circleBitmap)
        paint.color = Color.RED
        circleCanvas.drawCircle(120.dp, 60.dp, 60.dp, paint)
        val rectCanvas = Canvas(rectBitmap)
        paint.color = Color.BLUE
        rectCanvas.drawRect(0f, 60.dp, 120.dp, 180.dp, paint)
    }

    override fun onDraw(canvas: Canvas) {
        val saveCount = canvas.saveLayer(
            width / 4f,
            height / 2f - 90.dp,
            width / 2f + 90.dp,
            height / 2f + 90.dp,
            paint
        )
        canvas.drawBitmap(circleBitmap, width / 4f, height / 2f - 90.dp, paint)
        paint.xfermode = xfermode
        canvas.drawBitmap(rectBitmap, width / 4f, height / 2f - 90.dp, paint)
        paint.xfermode = null
        canvas.restoreToCount(saveCount)
    }

    private fun getPorterDuffModeNumber(porterDuffModeNumber: Int) =
        when (porterDuffModeNumber) {
            0 -> PorterDuff.Mode.CLEAR
            1 -> PorterDuff.Mode.SRC
            2 -> PorterDuff.Mode.DST
            3 -> PorterDuff.Mode.SRC_OVER
            4 -> PorterDuff.Mode.DST_OVER
            5 -> PorterDuff.Mode.SRC_IN
            6 -> PorterDuff.Mode.DST_IN
            7 -> PorterDuff.Mode.SRC_OUT
            8 -> PorterDuff.Mode.DST_OUT
            9 -> PorterDuff.Mode.SRC_ATOP
            10 -> PorterDuff.Mode.DST_ATOP
            11 -> PorterDuff.Mode.XOR
            12 -> PorterDuff.Mode.DARKEN
            13 -> PorterDuff.Mode.LIGHTEN
            14 -> PorterDuff.Mode.MULTIPLY
            15 -> PorterDuff.Mode.SCREEN
            16 -> PorterDuff.Mode.ADD
            17 -> PorterDuff.Mode.OVERLAY
            else -> throw IllegalAccessException("参数不正确：$porterDuffModeNumber")
        }
}