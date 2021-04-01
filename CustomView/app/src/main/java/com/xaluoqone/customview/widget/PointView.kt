package com.xaluoqone.customview.widget

import android.animation.TypeEvaluator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import com.xaluoqone.customview.ex.dp
import com.xaluoqone.customview.ex.sp

class PointView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeCap = Paint.Cap.ROUND
        textSize = 42.sp
        textAlign = Paint.Align.CENTER
    }

    private val fontMetrics = Paint.FontMetrics()

    var text:String = ""
        set(value) {
            field = value
            invalidate()
        }

    var radius = 20.dp
        set(value) {
            field = value
            invalidate()
        }

    var point = PointF(0f, 0f)
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {
        paint.strokeWidth = radius
        paint.color = Color.BLUE
        canvas.drawPoint(point.x, point.y, paint)
        paint.getFontMetrics(fontMetrics)
        paint.color = Color.WHITE
        canvas.drawText(
            text,
            width / 2f,
            height / 2 - (fontMetrics.ascent + fontMetrics.descent) / 2,
            paint
        )
    }
}

class PointFEvaluator : TypeEvaluator<PointF> {
    override fun evaluate(fraction: Float, startValue: PointF, endValue: PointF): PointF {
        val pointX = startValue.x + (endValue.x - startValue.x) * fraction
        val pointY = startValue.y + (endValue.y - startValue.y) * fraction
        return PointF(pointX, pointY)
    }
}

class TextEvaluator : TypeEvaluator<String> {
    override fun evaluate(fraction: Float, startValue: String, endValue: String): String {
        return province[(province.indexOf(startValue) + (province.indexOf(endValue) - province.indexOf(
            startValue
        )) * fraction).toInt()]
    }
}

val province = arrayOf(
    "北京市",
    "天津市",
    "上海市",
    "重庆市",
    "河北省",
    "山西省",
    "辽宁省",
    "吉林省",
    "黑龙江省",
    "江苏省",
    "浙江省",
    "安徽省",
    "福建省",
    "江西省",
    "山东省",
    "河南省",
    "湖北省",
    "湖南省",
    "广东省",
    "海南省",
    "四川省",
    "贵州省",
    "云南省",
    "陕西省",
    "甘肃省",
    "青海省",
    "台湾省",
    "内蒙古自治区",
    "广西壮族自治区",
    "西藏自治区",
    "宁夏回族自治区",
    "新疆维吾尔自治区",
    "香港特别行政区",
    "澳门特别行政区"
)