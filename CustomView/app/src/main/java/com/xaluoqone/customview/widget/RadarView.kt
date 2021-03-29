package com.xaluoqone.customview.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.xaluoqone.customview.ex.dp
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.cos
import kotlin.math.sin

class RadarView(context : Context , attrs : AttributeSet? = null) : View(context , attrs) {
    var datas : ArrayList<Float>

    var radius = 120.dp
        set(value) {
            field = value
            invalidate()
        }

    var sideCount = 6
        set(value) {
            field = value
            changeDatas()
            invalidate()
        }

    var circleCount = 6
        set(value) {
            field = value
            invalidate()
        }

    private val path = Path()

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 1.dp
        style = Paint.Style.STROKE
    }

    init {
        datas = arrayListOf(1.2f , 2.6f , 3.3f , 5f , 6f , 4.7f)
    }

    private fun changeDatas() {
        if (sideCount < datas.size) {
            val data = datas.subList(0 , sideCount)
            datas = ArrayList(data)
        }
        else if (sideCount > datas.size) {
            val needCount = sideCount - datas.size
            repeat(needCount) {
                datas.add(Random().nextFloat() * 10)
            }
        }
    }

    override fun onDraw(canvas : Canvas) {
        val centerX = width / 2f
        val centerY = height / 2f
        val angle = 360.0 / sideCount

        path.rewind()
        paint.color = Color.parseColor("#009688")
        repeat(circleCount) { count ->
            repeat(sideCount) {
                canvas.drawLine(centerX + (radius * (count + 1) / circleCount) * cos(Math.toRadians(it * angle)).toFloat() , centerY + (radius * (count + 1) / circleCount) * sin(Math.toRadians(it * angle)).toFloat() , centerX + (radius * (count + 1) / circleCount) * cos(Math.toRadians((it + 1) * angle)).toFloat() , centerY + (radius * (count + 1) / circleCount) * sin(Math.toRadians((it + 1) * angle)).toFloat() , paint)
            }
        }

        repeat(sideCount) {
            //原点坐标为x,y，则x1=x+距离*cos角度，y1=y+距离*sin角度
            canvas.drawLine(centerX , centerY , centerX + radius * cos(Math.toRadians(it * angle)).toFloat() , centerY + radius * sin(Math.toRadians(it * angle)).toFloat() , paint)

            val x = centerX + ((datas[it] / 10) * radius) * cos(Math.toRadians(it * angle)).toFloat()
            val y = centerY + ((datas[it] / 10) * radius) * sin(Math.toRadians(it * angle)).toFloat()
            if (it == 0) {
                path.moveTo(x , y)
            }
            else {
                path.lineTo(x , y)
            }
        }

        path.close()
        paint.color = Color.parseColor("#80fc3423")
        paint.style = Paint.Style.FILL
        canvas.drawPath(path , paint)
    }
}