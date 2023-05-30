package com.example.jetpack_demos.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.navArgs
import com.example.jetpack_demos.R
import com.example.jetpack_demos.databinding.ActivityDest7Binding

/**
 *
 */
class Dest7Activity : AppCompatActivity() {

    val args by navArgs<Dest7ActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDest7Binding =
            DataBindingUtil.setContentView(this, R.layout.activity_dest7)
        binding.textView.text = "userId=${args.userId}"
    }
}