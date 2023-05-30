package com.example.jetpack_demos.blur

import android.content.Context
import android.graphics.Canvas
import android.graphics.RenderEffect
import android.graphics.RenderNode
import android.graphics.Shader
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatImageView

/**
 * RenderNode包含绘制的位置、绘制的内容
 * RenderNode可以通过blurNode.beginRecording()获取Canvas，通过drawRenderNode完成嵌套RenderNode的效果，
 * 而每一个RenderNode可以有不同的RenderEffect，这个在某些时候比RuntimeShader更高效。
 * 而RuntimeShader是作用在整个View上，且必须使用AGSL语言，可以达到更强大更灵活的效果。
 */
class MyRenderNodeImageView(context: Context, attrs: AttributeSet?) :
    AppCompatImageView(context, attrs) {

    override fun onDraw(canvas: Canvas?) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val contentNode = RenderNode("image")
            val blurNode = RenderNode("blur")

            contentNode.setPosition(0, 0, width, height)
            val rnCanvas = contentNode.beginRecording()
            super.onDraw(rnCanvas)
            contentNode.endRecording()
            canvas?.drawRenderNode(contentNode)

            val h = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                40f,
                resources.displayMetrics
            )

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                blurNode.setRenderEffect(
                    RenderEffect.createBlurEffect(
                        30f, 30f,
                        Shader.TileMode.CLAMP
                    )
                )
            }
            blurNode.setPosition(0, 0, width, h.toInt())
            blurNode.translationY = height - h


            val blurCanvas = blurNode.beginRecording()
            blurCanvas.translate(0f, -(height - h))
            blurCanvas.drawRenderNode(contentNode)
            blurNode.endRecording()

            canvas?.drawRenderNode(blurNode)
        }
    }
}