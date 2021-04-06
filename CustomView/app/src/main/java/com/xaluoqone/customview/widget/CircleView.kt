package com.xaluoqone.customview.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.xaluoqone.customview.ex.dp

class CircleView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#009688")
    }
    private val defaultSize = 200.dp
    private val defaultPadding = 10.dp

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val size = defaultSize + defaultPadding
        val width = resolveSize(size.toInt(), widthMeasureSpec)
        val height = resolveSize(size.toInt(), heightMeasureSpec)
        setMeasuredDimension(width, height)
    }

    /**
     * 测量的基本套路：适用于大多数场景... 然而Android官方早已经封装了一个方法... 因此我选择官方{(@link #resolveSize(int,int))}
     */
    /*private fun getFinalSize(size: Int, measureSpec: Int): Int {
        val specSize = MeasureSpec.getSize(measureSpec)
        return when (MeasureSpec.getMode(measureSpec)) {
            MeasureSpec.EXACTLY -> {
                specSize
            }
            MeasureSpec.AT_MOST -> {
                if (size > specSize) specSize else size
            }
            else -> {
                size
            }
        }
    }*/

    override fun onDraw(canvas: Canvas) {
        val size = (defaultSize + defaultPadding) / 2
        canvas.drawCircle(
            size,
            size,
            defaultSize / 2,
            paint
        )
    }
}