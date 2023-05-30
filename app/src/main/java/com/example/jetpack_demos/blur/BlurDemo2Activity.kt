package com.example.jetpack_demos.blur

import android.graphics.RenderEffect
import android.graphics.RuntimeShader
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.jetpack_demos.R

/**
 *
 */
class BlurDemo2Activity : AppCompatActivity() {

    lateinit var tvTitle: TextView
    lateinit var iv: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blur_demo2)
        tvTitle = findViewById(R.id.tv)
        iv = findViewById(R.id.iv)

        Glide.with(this)
            .load("https://lh1.hetaousercontent.com/img/6fa592184d4aefd2.jpg?thumbnail=true")
            .into(iv)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            setShaderEffect()
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun setShaderEffect() {
        val FROSTED_GLASS_SHADER = RuntimeShader(
            """
        uniform shader inputShader;
        uniform float height;
        uniform float width;
                
        vec4 main(vec2 coords) {
            vec4 currValue = inputShader.eval(coords);
            float top = height;
            if (coords.y < top) {
                return currValue;
            } else {
                // Avoid blurring edges
                if (coords.x > 1 && coords.y > 1 &&
                        coords.x < (width - 1) &&
                        coords.y < (height - 1)) {
                    // simple box blur - average 5x5 grid around pixel
                    vec4 boxSum =
                        inputShader.eval(coords + vec2(-2, -2)) + 
                        // ...
                        currValue +
                        // ...
                        inputShader.eval(coords + vec2(2, 2));
                    currValue = boxSum / 25;
                }
                
                const vec4 white = vec4(1);
                // top-left corner of label area
                vec2 lefttop = vec2(0, top);
                float lightenFactor = min(1.0, .6 *
                        length(coords - lefttop) /
                        (0.85 * length(vec2(width, 100))));
                // White in upper-left, blended increasingly
                // toward lower-right
                return mix(currValue, white, 1 - lightenFactor);
            }
        }
            """
        )

        tvTitle.post {
            val w = tvTitle.width.toFloat()
            val ivH = iv.height.toFloat()
            val tvH = tvTitle.height.toFloat()
            val h = ivH - tvH

            FROSTED_GLASS_SHADER.setFloatUniform("height", h)
            FROSTED_GLASS_SHADER.setFloatUniform("width", w)
            val runtimeShaderEffect =
                RenderEffect.createRuntimeShaderEffect(FROSTED_GLASS_SHADER, "inputShader")
            iv.setRenderEffect(runtimeShaderEffect)
        }
    }
}