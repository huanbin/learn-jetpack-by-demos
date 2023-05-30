package com.example.jetpack_demos.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.navArgs
import com.example.jetpack_demos.R
import com.example.jetpack_demos.databinding.ActivityDest5ParamBinding

class Dest5ParamActivity : AppCompatActivity() {

    val args by navArgs<Dest5ParamActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDest5ParamBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_dest5_param)
        binding.textView.text= "${args.param1} need ${args.price}$"
    }
}