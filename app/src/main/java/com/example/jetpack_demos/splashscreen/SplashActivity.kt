package com.example.jetpack_demos.splashscreen

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.jetpack_demos.R

class SplashActivity : AppCompatActivity() {

    lateinit var splashScreen: SplashScreen
    lateinit var vm: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        //实例化SplashScreen并设置activity的Theme
        splashScreen = installSplashScreen()

        splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->
            val splashScreenView = splashScreenViewProvider.view
            val scaleY = ObjectAnimator.ofFloat(splashScreenView, View.SCALE_Y, 1.0f, 2.0f)
            val scaleX = ObjectAnimator.ofFloat(splashScreenView, View.SCALE_X, 1.0f, 2.0f)
            val alpha = ObjectAnimator.ofFloat(splashScreenView, View.ALPHA, 1f, 0f)
            val animatorSet = AnimatorSet()
//            animatorSet.setupStartValues()
//            animatorSet.setupEndValues()
            animatorSet.startDelay = 0
            //插值器，先向后，再向前冲
//            animatorSet.interpolator = AnticipateInterpolator()
            animatorSet.interpolator = LinearInterpolator()
            animatorSet.duration = 800
            animatorSet.playTogether(scaleX, scaleY, alpha)
            // Call SplashScreenView.remove at the end of your custom animation.
            animatorSet.doOnEnd { splashScreenViewProvider.remove() }
            // Run your animation.
            animatorSet.start()
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        vm = SplashViewModel()
        vm.initDatas()/*val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    println("onPreDraw...")
                    // Check whether the initial data is ready.
                    return if (vm.isReady.value!!) {
                        // The content is ready. Start drawing.
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        // The content isn't ready. Suspend.
                        false
                    }
                }
            }
        )*/
        splashScreen.setKeepOnScreenCondition { !vm.isReady.value!! }
    }
}