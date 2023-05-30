package com.example.jetpack_demos.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.jetpack_demos.R

class Fragment4CustomFactoryDemo(val userRepository: UserReopository) :
    Fragment(R.layout.fragment_demo4) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.textView).text = userRepository.toString()
    }
}