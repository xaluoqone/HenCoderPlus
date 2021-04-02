package com.xaluoqone.customview.widget

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.xaluoqone.customview.R
import com.xaluoqone.customview.ex.dp
import com.xaluoqone.customview.ex.sp

class MaterialEditText(context: Context, attrs: AttributeSet? = null) :
    AppCompatEditText(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val topHintX = 4.dp
    var topHintY = 43.dp
        set(value) {
            field = value
            invalidate()
        }

    var topHintTextSize = textSize
        set(value) {
            field = value
            invalidate()
        }

    var topHintTextColor = hintTextColors.defaultColor
        set(value) {
            field = value
            invalidate()
        }

    private var topHintShowing = false

    private val topHintTextSizeChangeAnimator by lazy {
        ObjectAnimator.ofFloat(this, "topHintTextSize", textSize, 12.sp)
    }

    private val topHintTextColorChangeAnimator by lazy {
        ObjectAnimator.ofArgb(this, "topHintTextColor", hintTextColors.defaultColor, Color.BLACK)
    }

    private val topHintYChangeAnimator by lazy {
        ObjectAnimator.ofFloat(this, "topHintY", 43.dp, 18.dp)
    }

    private val topHintAnimatorSet = AnimatorSet().apply {
        playTogether(
            topHintTextSizeChangeAnimator,
            topHintYChangeAnimator,
            topHintTextColorChangeAnimator
        )
        duration = 300
    }
    private var mShowTopHint: Boolean

    private val hintText = hint.toString()
    var showTopHint = false
        set(value) {
            field = value
            if (field) {
                hint = ""
                setPadding(paddingLeft, paddingTop + 18.dp.toInt(), paddingRight, paddingBottom)
            } else {
                hint = hintText
                setPadding(paddingLeft, paddingTop - 18.dp.toInt(), paddingRight, paddingBottom)
            }
            invalidate()
        }

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText)
        mShowTopHint = typedArray.getBoolean(R.styleable.MaterialEditText_showTopHint, true)
        if (mShowTopHint) {
            showTopHint = mShowTopHint
        }
        typedArray.recycle()
    }

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        if (text.isNullOrEmpty() && showTopHint) {
            if (focused && !topHintShowing) {
                topHintAnimatorSet.start()
                topHintShowing = true
            } else if (topHintShowing) {
                topHintAnimatorSet.reverse()
                topHintShowing = false
            }
        }
        if (focused && !showTopHint) {
            topHintY = 18.dp
            topHintTextSize = 12.sp
            topHintTextColor = Color.BLACK
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (showTopHint) {
            paint.textSize = topHintTextSize
            paint.color = topHintTextColor
            canvas.drawText(hintText, topHintX, topHintY, paint)
        }
    }
}