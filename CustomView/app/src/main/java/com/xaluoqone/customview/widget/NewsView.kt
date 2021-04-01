package com.xaluoqone.customview.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.fonts.Font
import android.util.AttributeSet
import android.view.View
import com.xaluoqone.customview.R
import com.xaluoqone.customview.ex.dp
import com.xaluoqone.customview.ex.getFitBitmap
import com.xaluoqone.customview.ex.sp

class NewsView(context: Context, attrs: AttributeSet? = null) :
    View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 18.sp
    }

    private val fontMetrics = Paint.FontMetrics()

    private val imageTopPadding = 50.dp
    private val imageRightPadding = 10.dp
    private val imageLeftPadding = 10.dp
    private val imageWidth = 150.dp

    override fun onDraw(canvas: Canvas) {
        val imageBitmap = context.getFitBitmap(R.mipmap.avatar, imageWidth.toInt())

        canvas.drawBitmap(
            imageBitmap,
            width - imageBitmap.width - imageRightPadding,
            imageTopPadding,
            paint
        )

        val text = resources.getString(R.string.news)

        var start = 0
        var count: Int
        paint.getFontMetrics(fontMetrics)
        var y = -fontMetrics.top
        while (start < text.length) {
            val textRowWidth =
                if (y < imageTopPadding || y - paint.fontSpacing > imageTopPadding + imageBitmap.height) width.toFloat() else width - imageBitmap.width - imageRightPadding - imageLeftPadding
            count =
                paint.breakText(text, start, text.length, true, textRowWidth, null)
            canvas.drawText(text, start, start + count, 0f, y, paint)
            start += count
            y += paint.fontSpacing
        }
    }
}