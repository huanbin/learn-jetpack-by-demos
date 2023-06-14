package com.example.jetpack_demos.view.cutout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.example.jetpack_demos.databinding.ActivityDemoDisplayCutout1Binding
import java.util.Arrays

/**
 * 通过样式设置windowLayoutInDisplayCutoutMode属性
 */
class DisplayCutoutDemo1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDemoDisplayCutout1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //decor view是否填充content view到insets，并且将insets传递给content view
        WindowCompat.setDecorFitsSystemWindows(window, false)

        ViewCompat.setOnApplyWindowInsetsListener(
            binding.imageView
        ) { v, insets ->
            val displayCutout = insets.displayCutout
            /*val safeInsetTop = displayCutout?.safeInsetTop
                        safeInsetTop?.let {
                            v.updatePadding(
                                top = it
                            )
                        }*/
            val safeInsetBottom = displayCutout?.safeInsetBottom
            safeInsetBottom?.let {
                v.updatePadding(
                    //反复操作出现bug
                    //bottom = v.paddingBottom + it
                    bottom = v.paddingBottom
                )
            }
            WindowInsetsCompat.CONSUMED
        }

        //使用window相关api，因为window的大小并不一定是整个屏幕大小
        var posInWindow = IntArray(2)
        binding.imageView.getLocationInWindow(posInWindow)
        println("posInWindow=${Arrays.toString(posInWindow)}")
        val posInScreen = IntArray(2)
        binding.imageView.getLocationOnScreen(posInScreen)
        println("posInScreen=${Arrays.toString(posInScreen)}")
    }
}