package com.example.jetpack_demos.view.constraintlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jetpack_demos.R

/**
 * 约束偏差
 * 默认情况2端设置约束就会居中显示（fix、wrap_content尺寸）
 */
class ConstraintLayoutDemo2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_layout_demo2)
    }
}