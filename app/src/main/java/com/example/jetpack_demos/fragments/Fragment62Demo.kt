package com.example.jetpack_demos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.example.jetpack_demos.R

//最小的fragment
class Fragment62Demo : Fragment(R.layout.fragment_demo6) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(R.transition.slide_right)
    }
}