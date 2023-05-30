package com.example.jetpack_demos.blur

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.jetpack_demos.R

class BlurDemo1Activity : AppCompatActivity() {

    lateinit var tvTitle: TextView
    lateinit var iv: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blur_demo1)

        val ivBack = findViewById<ImageView>(R.id.ivImage)
        Glide.with(this)
            .load("https://lh1.hetaousercontent.com/img/68c5e00d558c57ed.jpg?thumbnail=true")
            .into(ivBack)
        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateEffect(ivBack, progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    private fun updateEffect(view: View, progress: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (progress > 0) {
                view.setRenderEffect(
                    RenderEffect.createBlurEffect(
                        progress.toFloat(),
                        progress.toFloat(),
                        Shader.TileMode.REPEAT
                    )
                )
            } else {
                //移除RenderEffect
                view.setRenderEffect(null)
            }
        }
    }
}