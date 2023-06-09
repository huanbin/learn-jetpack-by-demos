package com.example.jetpack_demos.view.constraintlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jetpack_demos.R

/**
 * 约束布局
 * 1.子view必须在x轴和y轴都至少有一个约束
 * 2.每一个约束处理具柄，只能有一个约束。但是其它View可以有多个约束指向这个锚点
 * 3.约束辅助线guideline，用于设置view相对辅助线的位置
 * 4.约束屏障barrier，用于设置管理一组view（内部的view也可以设置约束到屏障），让别的view设置相对这一组view的位置
 * 5.约束偏差bias，用于调整约束偏差。如果view的尺寸是fix或者wrap_content，在view两端都设置约束，默认情况下，view会在2个约束之间居中显示，此时bias为50%。
 *   约束偏差，有垂直和水平约束偏差
 * 6.View的尺寸，有3种模式。固定大小、包裹内容大小、匹配约束大小。其中包裹内容大小是不受约束限制的，可以通过
 */
class ConstraintLayoutDemo1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_layout_demo1)
    }
}