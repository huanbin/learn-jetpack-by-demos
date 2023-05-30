package com.example.jetpack_demos.navigation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.jetpack_demos.R
import com.example.jetpack_demos.databinding.FragmentDest2Binding

/**
 */
class Dest2Fragment : BaseFragment<FragmentDest2Binding>() {

    override fun layout(): Int = R.layout.fragment_dest2

    val args by navArgs<Dest2FragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mtvWithParam.text = "title=${args.title},number=${args.number}"
    }

    override fun onClick(v: View?) {

    }
}