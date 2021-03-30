package com.xaluoqone.customview.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
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

    var imageTopPadding = 50.dp
    var imageRightPadding = 10.dp
    var imageLeftPadding = 10.dp
    var imageWidth = 150.dp

    override fun onDraw(canvas: Canvas) {
        val imageBitmap = context.getFitBitmap(R.mipmap.avatar, imageWidth.toInt())

        canvas.drawBitmap(
            imageBitmap,
            width - imageBitmap.width - imageRightPadding ,
            imageTopPadding,
            paint
        )

        val text = resources.getString(R.string.news)

        var start = 0
        var count: Int
        val fontMetrics = paint.fontMetrics
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