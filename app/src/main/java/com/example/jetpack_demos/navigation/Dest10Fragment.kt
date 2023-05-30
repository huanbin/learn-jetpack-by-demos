package com.example.jetpack_demos.navigation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.jetpack_demos.R
import com.example.jetpack_demos.databinding.FragmentDest10Binding

class Dest10Fragment : BaseFragment<FragmentDest10Binding>() {

    override fun layout(): Int = R.layout.fragment_dest10

    val nav by navArgs<Dest10FragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvDogName.text = nav.dogName
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}