package com.xaluoqone.customview

import android.animation.ObjectAnimator
import android.graphics.PointF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import com.xaluoqone.customview.ex.dp
import com.xaluoqone.customview.widget.PointFEvaluator
import com.xaluoqone.customview.widget.PointView
import com.xaluoqone.customview.widget.RoundView

class AnimatorActivity : AppCompatActivity() {
    private lateinit var point: PointView
    private lateinit var round: RoundView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animator)

        point = findViewById(R.id.point)
        round = findViewById(R.id.roundView)

        val pointAnimator = ObjectAnimator.ofObject(
            point, "point", PointFEvaluator(),
            PointF(90.dp, 190.dp)
        )

        pointAnimator.duration = 1500
        pointAnimator.start()

        val radiusAnimator = ObjectAnimator.ofFloat(round, "radius", 100.dp)
        //radiusAnimator.interpolator= DecelerateInterpolator()
        radiusAnimator.startDelay=2000
        radiusAnimator.start()
    }
}