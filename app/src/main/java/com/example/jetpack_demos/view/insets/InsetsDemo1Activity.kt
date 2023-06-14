package com.example.jetpack_demos.view.insets

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.view.WindowInsets
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MarginLayoutParamsCompat
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import com.example.jetpack_demos.databinding.ActivityDemoInsets1Binding

class InsetsDemo1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDemoInsets1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //step1
        WindowCompat.setDecorFitsSystemWindows(window, false)
        //step2
        //window.statusBarColor=Color.TRANSPARENT
        val windowInsetsControllerCompat = WindowInsetsControllerCompat(window, window.decorView)
        windowInsetsControllerCompat.isAppearanceLightStatusBars = false
        windowInsetsControllerCompat.isAppearanceLightNavigationBars = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            //加了一个窗帘
            window.isStatusBarContrastEnforced = true
            window.isNavigationBarContrastEnforced = true
        }

        binding.fab.setOnClickListener {
            Toast.makeText(this, "click fab!", Toast.LENGTH_SHORT).show()
        }
        //fix fab 遮盖问题
        ViewCompat.setOnApplyWindowInsetsListener(
            binding.fab
        ) { v, insets ->
            v.updateLayoutParams<MarginLayoutParams> {
                val gesturesInsets = insets.getInsets(WindowInsetsCompat.Type.systemGestures())
                rightMargin += gesturesInsets.right
                bottomMargin += insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
            }

            WindowInsetsCompat.CONSUMED
        }
    }
}