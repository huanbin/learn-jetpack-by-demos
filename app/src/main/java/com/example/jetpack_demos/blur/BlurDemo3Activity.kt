package com.example.jetpack_demos.blur

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.jetpack_demos.R

/**
 *
 */
class BlurDemo3Activity : AppCompatActivity() {

    lateinit var tvTitle: TextView
    lateinit var iv: MyRenderNodeImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blur_demo3)
        tvTitle = findViewById(R.id.tv)
        iv = findViewById(R.id.iv)

        Glide.with(this)
            .load("https://lh1.hetaousercontent.com/img/6fa592184d4aefd2.jpg?thumbnail=true")
            .into(iv)
    }
}