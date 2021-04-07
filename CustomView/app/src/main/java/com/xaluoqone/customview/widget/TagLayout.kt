package com.xaluoqone.customview.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.core.view.*
import kotlin.math.max

class TagLayout(context: Context, attrs: AttributeSet? = null) :
    ViewGroup(context, attrs) {
    private val childrenLayoutInfo = mutableListOf<Rect>()

    @SuppressLint("DrawAllocation")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var lineHeight = 0
        val selfWidth = MeasureSpec.getSize(widthMeasureSpec)
        val selfHeight = MeasureSpec.getSize(heightMeasureSpec)
        var nextChildX = 0
        var finalWidth = 0
        var finalHeight = 0
        children.forEachIndexed { index, it ->
            val marginStartAndEnd = it.marginStart + it.marginEnd
            getChildMeasureSpec(it, (selfWidth - marginStartAndEnd), selfHeight)

            if (nextChildX + it.measuredWidth + marginStartAndEnd > selfWidth) {
                nextChildX = 0
                finalHeight += lineHeight
                lineHeight = 0
            }
            nextChildX += it.marginStart
            val measuredHeightWithTopAndBottom = it.measuredHeight + it.marginTop + it.marginBottom
            childrenLayoutInfo.add(
                Rect(
                    nextChildX,
                    (finalHeight + it.marginTop),
                    (nextChildX + it.measuredWidth),
                    (finalHeight + measuredHeightWithTopAndBottom)
                )
            )
            nextChildX += it.measuredWidth + it.marginEnd
            finalWidth = max(nextChildX, finalWidth)
            lineHeight = max(lineHeight, measuredHeightWithTopAndBottom)
            if (index == childCount - 1) {
                finalHeight += lineHeight
            }
        }

        setMeasuredDimension(
            resolveSize(finalWidth, widthMeasureSpec),
            resolveSize(finalHeight, heightMeasureSpec)
        )
    }

    private fun getChildMeasureSpec(child: View, usableWidth: Int, usableHeight: Int) {
        val layoutParams = child.layoutParams
        val childWidthMeasureSpec = when (layoutParams.width) {
            LayoutParams.WRAP_CONTENT -> {
                MeasureSpec.makeMeasureSpec(usableWidth, MeasureSpec.AT_MOST)
            }
            else -> {
                MeasureSpec.makeMeasureSpec(usableWidth, MeasureSpec.EXACTLY)
            }
        }

        val childHeightMeasureSpec = when (layoutParams.height) {
            LayoutParams.WRAP_CONTENT -> {
                MeasureSpec.makeMeasureSpec(usableHeight, MeasureSpec.AT_MOST)
            }
            else -> {
                MeasureSpec.makeMeasureSpec(usableHeight, MeasureSpec.EXACTLY)
            }
        }
        child.measure(childWidthMeasureSpec, childHeightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        children.forEachIndexed { index, view ->
            childrenLayoutInfo[index].run {
                view.layout(left, top, right, bottom)
            }
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }
}