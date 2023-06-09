package com.example.jetpack_demos.view.constraintlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.TransitionManager
import com.example.jetpack_demos.R
import com.example.jetpack_demos.databinding.ActivityConstraintLayoutDemo4Binding

/**
 * 动画
 */
class ConstraintLayoutDemo4Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityConstraintLayoutDemo4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btRunAnimation.isEnabled=true
        binding.btRunResetnimation.isEnabled=false

        binding.btRunAnimation.setOnClickListener {
            val constraintSet = ConstraintSet()
            constraintSet.load(this,R.layout.activity_constraint_layout_demo4_2)
            TransitionManager.beginDelayedTransition(binding.constraintLayout)
            constraintSet.applyTo(binding.constraintLayout)
            binding.btRunResetnimation.isEnabled=true
            binding.btRunAnimation.isEnabled=false
        }
        binding.btRunResetnimation.setOnClickListener {
            val constraintSet = ConstraintSet()
            constraintSet.load(this,R.layout.activity_constraint_layout_demo4)
            TransitionManager.beginDelayedTransition(binding.constraintLayout)
            constraintSet.applyTo(binding.constraintLayout)
            binding.btRunResetnimation.isEnabled=false
            binding.btRunAnimation.isEnabled=true
        }

    }
}