package com.example.jetpack_demos.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpack_demos.databinding.ActivityDemoTranslationyBinding

/**
 * 理解translationY属性
 */
class TranslationYDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDemoTranslationyBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}