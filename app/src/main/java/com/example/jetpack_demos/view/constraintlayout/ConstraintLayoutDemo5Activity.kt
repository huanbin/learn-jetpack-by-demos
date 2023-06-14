package com.example.jetpack_demos.view.constraintlayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpack_demos.databinding.ActivityConstraintLayoutDemo5Binding
import com.example.jetpack_demos.databinding.ActivityDemoTranslationyBinding

/**
 * 当view的尺寸为wrap_content时，默认不遵守约束条件。
 * 添加app:layout_constrainedWidth="true"，可见margin的生效。
 */
class ConstraintLayoutDemo5Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityConstraintLayoutDemo5Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}