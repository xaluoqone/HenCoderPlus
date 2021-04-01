package com.xaluoqone.customview

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.PointF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.animation.AccelerateInterpolator
import androidx.core.animation.addListener
import com.xaluoqone.customview.ex.dp
import com.xaluoqone.customview.widget.*

class AnimatorActivity : AppCompatActivity() {
    private lateinit var point: PointView
    private lateinit var transformView: TransformView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animator)

        point = findViewById(R.id.point)
        transformView = findViewById(R.id.transformView)

        val animatorSet1 = AnimatorSet()
        point.post {
            val pointAnimator = ObjectAnimator.ofObject(
                point, "point", PointFEvaluator(),
                PointF(point.width / 2f, point.height / 2f)
            )
            pointAnimator.interpolator = AccelerateInterpolator()
            pointAnimator.duration = 1500

            val radiusAnimator = ObjectAnimator.ofFloat(point, "radius", 300.dp)
            radiusAnimator.duration = 1000
            animatorSet1.playSequentially(pointAnimator, radiusAnimator)
        }

        val textAnimator = ObjectAnimator.ofObject(
            point, "text", TextEvaluator(),
            province[0],
            province[province.lastIndex]
        )
        textAnimator.duration = 2500

        animatorSet1.addListener(onEnd = {
            textAnimator.start()
        })

        val cameraBottomRotateXAnimator =
            ObjectAnimator.ofFloat(transformView, "cameraBottomRotateX", 60f)
        cameraBottomRotateXAnimator.duration = 1000
        val canvasBottomRotateAnimator =
            ObjectAnimator.ofFloat(transformView, "canvasBottomRotate", 270f)
        canvasBottomRotateAnimator.duration = 1500
        val cameraTopRotateXAnimator =
            ObjectAnimator.ofFloat(transformView, "cameraTopRotateX", -60f)
        cameraTopRotateXAnimator.duration = 1000

        val animatorSet = AnimatorSet()
        animatorSet.playSequentially(
            cameraBottomRotateXAnimator,
            canvasBottomRotateAnimator,
            cameraTopRotateXAnimator
        )
        animatorSet.addListener(onEnd = {
            transformView.visibility = GONE
            point.visibility = VISIBLE
            animatorSet1.start()
        })
        animatorSet.start()
    }
}