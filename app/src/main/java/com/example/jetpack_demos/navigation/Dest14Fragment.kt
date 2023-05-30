package com.example.jetpack_demos.navigation

import android.os.Bundle
import android.view.View
import androidx.transition.TransitionInflater
import com.example.jetpack_demos.R
import com.example.jetpack_demos.databinding.FragmentDest14Binding

class Dest14Fragment : BaseFragment<FragmentDest14Binding>() {

    override fun layout() = R.layout.fragment_dest14

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}