package com.example.jetpack_demos.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.jetpack_demos.R
import com.example.jetpack_demos.databinding.ActivityDest6Binding

/*
* 隐式intent
* */
class Dest6Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDest6Binding =
            DataBindingUtil.setContentView(this, R.layout.activity_dest6)

    }
}