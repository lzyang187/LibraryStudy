package com.iflytek.librarystudy.lottie

import android.animation.Animator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.airbnb.lottie.LottieAnimationView
import com.iflytek.librarystudy.R

class LottieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie)
        val lottieView = findViewById<LottieAnimationView>(R.id.lottie_view)
        val lottieAnimPath = "lottie/star"
        lottieView.imageAssetsFolder = "$lottieAnimPath/images"
        lottieView.setAnimation("$lottieAnimPath/data.json")

        lottieView.playAnimation()
//        lottieView.pauseAnimation()
//        lottieView.cancelAnimation()
        lottieView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
                Log.d(TAG, "onAnimationStart: ")
            }

            override fun onAnimationEnd(animation: Animator?) {
                Log.d(TAG, "onAnimationEnd: ")
            }

            override fun onAnimationCancel(animation: Animator?) {
                Log.d(TAG, "onAnimationCancel: ")
            }

            override fun onAnimationRepeat(animation: Animator?) {
                Log.d(TAG, "onAnimationRepeat: ")
            }

        })
    }

    companion object {
        private const val TAG = "LottieActivity"
    }

}