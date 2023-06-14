package com.example.jetpack_demos.view.animatekeyboard

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsAnimationCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updateLayoutParams
import com.example.jetpack_demos.databinding.ActivityDemoAnimateKeyboard1Binding

/**
 * 通过样式设置windowLayoutInDisplayCutoutMode属性
 */
class AnimateKeyboardDemo1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDemoAnimateKeyboard1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //decor view是否填充content view到insets，并且将insets传递给content view
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val controllerCompat = WindowInsetsControllerCompat(window, window.decorView)
        controllerCompat.isAppearanceLightStatusBars = true
        controllerCompat.isAppearanceLightNavigationBars = true
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.navigationBarDividerColor = Color.YELLOW
        }
        //ViewCompat.getRootWindowInsets(binding.textInputLayout)
        ViewCompat.setOnApplyWindowInsetsListener(
            binding.textInputLayout
        ) { v, insets ->
            //检查键盘的可见性
            val imeVisible = insets.isVisible(WindowInsetsCompat.Type.ime())
            println("imeVisible=$imeVisible")
            val imeHeight = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            println("imeHeight=$imeHeight")

            v.updateLayoutParams<MarginLayoutParams> {
                //注意这里需要处理navbar和ime的inset
                bottomMargin =
                    insets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom + imeHeight
            }
            WindowInsetsCompat.CONSUMED
        }


        val view = binding.textInputLayout
        ViewCompat.setWindowInsetsAnimationCallback(view,
            object : WindowInsetsAnimationCompat.Callback(DISPATCH_MODE_STOP) {

                var startBottom = 0f

                override fun onPrepare(
                    animation: WindowInsetsAnimationCompat
                ) {
                    startBottom = view.bottom.toFloat()
                    println("startBottom=$startBottom")
                }

                var endBottom = 0f

                override fun onStart(
                    animation: WindowInsetsAnimationCompat,
                    bounds: WindowInsetsAnimationCompat.BoundsCompat
                ): WindowInsetsAnimationCompat.BoundsCompat {
                    // Record the position of the view after the IME transition.
                    endBottom = view.bottom.toFloat()
                    println("endBottom=$endBottom")
                    return bounds
                }

                override fun onProgress(
                    insets: WindowInsetsCompat,
                    runningAnimations: MutableList<WindowInsetsAnimationCompat>
                ): WindowInsetsCompat {
                    // Find an IME animation.
                    val imeAnimation = runningAnimations.find {
                        it.typeMask and WindowInsetsCompat.Type.ime() != 0
                    } ?: return insets

                    view.updateLayoutParams<MarginLayoutParams> {
                        bottomMargin = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
                    }

                    //此时布局已经完成了！也就是说其实edittext已经在键盘上面，只是有一个translateY=startBottom - endBottom
                    //我们只需要反向的减小translateY直到为0
                    val translateY =
                        (startBottom - endBottom) * (1 - imeAnimation.interpolatedFraction)

                    // Offset the view based on the interpolated fraction of the IME animation.
                    view.translationY = translateY

//                    println("onProgress:imeAnimation.interpolatedFraction=${imeAnimation.interpolatedFraction}")
//                    println("onProgress:(startBottom - endBottom)=${startBottom - endBottom}")
                    println("onProgress:translateY=$translateY")
                    return insets
                }
            })
    }
}