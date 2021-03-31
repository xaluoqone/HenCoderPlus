package com.xaluoqone.customview

import android.animation.ObjectAnimator
import android.graphics.PointF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import com.xaluoqone.customview.ex.dp
import com.xaluoqone.customview.widget.PointFEvaluator
import com.xaluoqone.customview.widget.PointView

class AnimatorActivity : AppCompatActivity() {
    private lateinit var point: PointView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animator)

        point = findViewById(R.id.point)

        point.post {
            val pointAnimator = ObjectAnimator.ofObject(
                point, "point", PointFEvaluator(),
                PointF(point.width / 2f, point.height / 2f)
            )
            pointAnimator.interpolator = AccelerateInterpolator()
            pointAnimator.duration = 1500
            pointAnimator.start()

            val radiusAnimator = ObjectAnimator.ofFloat(point, "radius", 300.dp)
            radiusAnimator.startDelay = 1500
            radiusAnimator.duration = 1000
            radiusAnimator.start()
        }
    }
}