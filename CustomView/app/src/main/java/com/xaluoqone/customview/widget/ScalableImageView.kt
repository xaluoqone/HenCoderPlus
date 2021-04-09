package com.xaluoqone.customview.widget

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.OverScroller
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.ViewCompat
import com.xaluoqone.customview.R
import com.xaluoqone.customview.ex.dp
import com.xaluoqone.customview.ex.getFitBitmap
import kotlin.math.max
import kotlin.math.min

class ScalableImageView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = context.getFitBitmap(R.mipmap.mz, 300.dp.toInt())
    private var offsetX = 0f
    private var offsetY = 0f
    private var defaultOffsetX = 0f
    private var defaultOffsetY = 0f
    private var smallScale = 0f
    private var bigScale = 0f
    private var isBigSale = false
    private var extraScale = 2f
    private val imageGestureListener = ImageGestureListener()
    private val gestureDetector = GestureDetectorCompat(context, imageGestureListener)
    private val scroller = OverScroller(context)
    private val flingRunnable = FlingRunnable()

    var scaleProgress = 0f
        set(value) {
            field = value
            invalidate()
        }

    private val scaleAnimator by lazy {
        ObjectAnimator.ofFloat(this@ScalableImageView, "scaleProgress", 0f, 1f)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        defaultOffsetX = width / 2 - bitmap.width / 2f
        defaultOffsetY = height / 2 - bitmap.height / 2f

        if (bitmap.width / bitmap.height.toFloat() > width / height.toFloat()) {
            bigScale = height / bitmap.height.toFloat() * extraScale
            smallScale = width / bitmap.width.toFloat()
        } else {
            bigScale = width / bitmap.width.toFloat() * extraScale
            smallScale = height / bitmap.height.toFloat()
        }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.translate(offsetX, offsetY)
        val scale = smallScale + (bigScale - smallScale) * scaleProgress
        canvas.scale(scale, scale, width / 2f, height / 2f)
        canvas.drawBitmap(bitmap, defaultOffsetX, defaultOffsetY, paint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    private inner class ImageGestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent) = true

        override fun onDoubleTap(e: MotionEvent): Boolean {
            isBigSale = !isBigSale
            if (isBigSale) {
                scaleAnimator.start()
            } else {
                scaleAnimator.reverse()
            }
            return false
        }

        /**
         * 说是onScroll，但是它是和ON MOVE事件一样的，即手指后续在屏幕上触摸会调用这个事件
         *
         * @param downEvent 手指刚放到屏幕上的第一个事件
         * @param currentEvent 当前手指触发的事件
         * @param distanceX 当前位置的X和上一个位置的X的距离    注意：这个距离是上一个位置减去当前位置得来的
         * @param distanceY 当前位置的Y和上一个位置的Y的距离    注意：这个距离是上一个位置减去当前位置得来的
         */
        override fun onScroll(
            downEvent: MotionEvent,
            currentEvent: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            if (isBigSale) {
                offsetX -= distanceX
                offsetX = max(offsetX, -(bitmap.width * bigScale - width) / 2)
                offsetX = min(offsetX, (bitmap.width * bigScale - width) / 2)
                offsetY -= distanceY
                offsetY = max(offsetY, -(bitmap.height * bigScale - height) / 2)
                offsetY = min(offsetY, (bitmap.height * bigScale - height) / 2)
                invalidate()
            }
            return false
        }

        override fun onFling(
            downEvent: MotionEvent,
            currentEvent: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            if (isBigSale) {
                scroller.fling(
                    offsetX.toInt(),
                    offsetY.toInt(),
                    velocityX.toInt(),
                    velocityY.toInt(),
                    -((bitmap.width * bigScale - width) / 2).toInt(),
                    ((bitmap.width * bigScale - width) / 2).toInt(),
                    -((bitmap.height * bigScale - height) / 2).toInt(),
                    ((bitmap.height * bigScale - height) / 2).toInt(),
                    30.dp.toInt(),
                    30.dp.toInt()
                )

                ViewCompat.postOnAnimation(this@ScalableImageView, flingRunnable)
            }
            return false
        }
    }

    private inner class FlingRunnable : Runnable {
        override fun run() {
            if (scroller.computeScrollOffset()) {
                offsetX = scroller.currX.toFloat()
                offsetY = scroller.currY.toFloat()
                invalidate()
                ViewCompat.postOnAnimation(this@ScalableImageView, flingRunnable)
            }
        }
    }
}