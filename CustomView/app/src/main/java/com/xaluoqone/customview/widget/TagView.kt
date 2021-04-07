package com.xaluoqone.customview.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.xaluoqone.customview.R
import com.xaluoqone.customview.ex.sp

class TagView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = (15..25).random().sp
    }
    private val bounds = Rect()
    private val fontMetrics = Paint.FontMetrics()
    private var selfWidth = 0
    private var selfHeight = 0
    var radius: Float
    var text: String

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TagView)
        text = typedArray.getString(R.styleable.TagView_tagText) ?: ""
        radius = typedArray.getDimension(R.styleable.TagView_radius, 0f)
        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        paint.getTextBounds(text, 0, text.length, bounds)
        paint.getFontMetrics(fontMetrics)
        selfWidth = bounds.width() + paddingStart + paddingEnd
        selfHeight = (fontMetrics.bottom - fontMetrics.top).toInt() + paddingTop + paddingBottom

        setMeasuredDimension(
            resolveSize(selfWidth, widthMeasureSpec),
            resolveSize(selfHeight, heightMeasureSpec)
        )
    }

    override fun onDraw(canvas: Canvas) {
        paint.color =
            Color.argb(255, (50..160).random(), (50..160).random(), (50..160).random())
        canvas.drawRoundRect(
            0f,
            0f,
            width.toFloat(),
            height.toFloat(),
            radius,
            radius,
            paint
        )
        paint.color = Color.parseColor("#ffffff")
        canvas.drawText(
            text,
            paddingStart - bounds.left.toFloat(),
            paddingTop - fontMetrics.top,
            paint
        )
    }
}