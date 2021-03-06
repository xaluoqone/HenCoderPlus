package com.xaluoqone.customview.widget

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.OverScroller
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.ViewCompat
import com.xaluoqone.customview.R
import com.xaluoqone.customview.ex.dp
import com.xaluoqone.customview.ex.getFitBitmap
import kotlin.math.max
import kotlin.math.min

class ScalableImageView(context : Context , attrs : AttributeSet? = null) : View(context , attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = context.getFitBitmap(R.mipmap.avatar , 300.dp.toInt())
    private var offsetX = 0f
    private var offsetY = 0f
    private var defaultOffsetX = 0f
    private var defaultOffsetY = 0f
    private var smallScale = 0f
    private var bigScale = 0f
    private var isBigSale = false
    private var extraScale = 2f
    private val imageGestureListener = ImageGestureListener()
    private val gestureDetector = GestureDetectorCompat(context , imageGestureListener)
    private val imageScaleGestureListener = ImageScaleGestureListener()
    private val scaleGestureDetector = ScaleGestureDetector(context , imageScaleGestureListener)
    private val scroller = OverScroller(context)
    private val flingRunnable = FlingRunnable()

    var currentScale = 0f
        set(value) {
            field = value
            invalidate()
        }

    private val scaleAnimator = ObjectAnimator.ofFloat(this@ScalableImageView , "currentScale" ,
        smallScale , bigScale)

    override fun onSizeChanged(w : Int , h : Int , oldw : Int , oldh : Int) {
        super.onSizeChanged(w , h , oldw , oldh)

        defaultOffsetX = width / 2 - bitmap.width / 2f
        defaultOffsetY = height / 2 - bitmap.height / 2f

        if (bitmap.width / bitmap.height.toFloat() > width / height.toFloat()) {
            bigScale = height / bitmap.height.toFloat() * extraScale
            smallScale = width / bitmap.width.toFloat()
        }
        else {
            bigScale = width / bitmap.width.toFloat() * extraScale
            smallScale = height / bitmap.height.toFloat()
        }
        scaleAnimator.setFloatValues(smallScale , bigScale)
        currentScale = smallScale
    }

    override fun onDraw(canvas : Canvas) {
        val scaleProgress = (currentScale - smallScale) / (bigScale - smallScale)
        canvas.translate(offsetX * scaleProgress , offsetY * scaleProgress)
        canvas.scale(currentScale , currentScale , width / 2f , height / 2f)
        canvas.drawBitmap(bitmap , defaultOffsetX , defaultOffsetY , paint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event : MotionEvent) : Boolean {
        scaleGestureDetector.onTouchEvent(event)
        if (!scaleGestureDetector.isInProgress) {
            gestureDetector.onTouchEvent(event)
        }
        return true
    }

    private inner class ImageGestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e : MotionEvent) = true

        override fun onDoubleTap(e : MotionEvent) : Boolean {
            isBigSale = !isBigSale
            if (isBigSale) {
                offsetX = (e.x - width / 2) * (1 - bigScale / smallScale)
                offsetY = (e.y - height / 2) * (1 - bigScale / smallScale)
                fixOffsets()
                scaleAnimator.start()
            }
            else {
                scaleAnimator.reverse()
            }
            return false
        }

        /**
         * ??????onScroll??????????????????ON MOVE????????????????????????????????????????????????????????????????????????
         *
         * @param downEvent ??????????????????????????????????????????
         * @param currentEvent ???????????????????????????
         * @param distanceX ???????????????X?????????????????????X?????????    ??????????????????????????????????????????????????????????????????
         * @param distanceY ???????????????Y?????????????????????Y?????????    ??????????????????????????????????????????????????????????????????
         */
        override fun onScroll(downEvent : MotionEvent , currentEvent : MotionEvent ,
            distanceX : Float , distanceY : Float) : Boolean {
            if (isBigSale) {
                offsetX -= distanceX
                offsetY -= distanceY
                fixOffsets()
                invalidate()
            }
            return false
        }

        private fun fixOffsets() {
            offsetX = max(offsetX , -(bitmap.width * bigScale - width) / 2)
            offsetX = min(offsetX , (bitmap.width * bigScale - width) / 2)
            offsetY = max(offsetY , -(bitmap.height * bigScale - height) / 2)
            offsetY = min(offsetY , (bitmap.height * bigScale - height) / 2)
        }

        override fun onFling(downEvent : MotionEvent , currentEvent : MotionEvent ,
            velocityX : Float , velocityY : Float) : Boolean {
            if (isBigSale) {
                scroller.fling(offsetX.toInt() , offsetY.toInt() , velocityX.toInt() ,
                    velocityY.toInt() , -((bitmap.width * bigScale - width) / 2).toInt() ,
                    ((bitmap.width * bigScale - width) / 2).toInt() ,
                    -((bitmap.height * bigScale - height) / 2).toInt() ,
                    ((bitmap.height * bigScale - height) / 2).toInt() )

                ViewCompat.postOnAnimation(this@ScalableImageView , flingRunnable)
            }
            return false
        }
    }

    private inner class ImageScaleGestureListener : ScaleGestureDetector.OnScaleGestureListener {
        override fun onScaleBegin(detector : ScaleGestureDetector) : Boolean {
            offsetX = (detector.focusX - width / 2) * (1 - bigScale / smallScale)
            offsetY = (detector.focusY - height / 2) * (1 - bigScale / smallScale)
            return true
        }

        override fun onScale(detector : ScaleGestureDetector) : Boolean {
            val tempScale = currentScale * detector.scaleFactor
            return if (tempScale > bigScale || tempScale < smallScale) false
            else {
                currentScale *= detector.scaleFactor
                isBigSale = currentScale > smallScale
                true
            }
        }

        override fun onScaleEnd(detector : ScaleGestureDetector?) {

        }
    }

    private inner class FlingRunnable : Runnable {
        override fun run() {
            if (scroller.computeScrollOffset()) {
                offsetX = scroller.currX.toFloat()
                offsetY = scroller.currY.toFloat()
                invalidate()
                ViewCompat.postOnAnimation(this@ScalableImageView , flingRunnable)
            }
        }
    }
}