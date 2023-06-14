package com.example.jetpack_demos.view.insets

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.jetpack_demos.databinding.ActivityDemoInsets1Binding

class InsetsDemo2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDemoInsets1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //decor view是否填充content view到insets，并且将insets传递给content view
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val windowInsetsControllerCompat = WindowInsetsControllerCompat(window, window.decorView)
        windowInsetsControllerCompat.hide(WindowInsetsCompat.Type.systemBars())
        windowInsetsControllerCompat.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}