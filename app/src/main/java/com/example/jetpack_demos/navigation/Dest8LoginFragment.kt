package com.example.jetpack_demos.navigation

import android.os.Bundle
import android.view.View
import com.example.jetpack_demos.R
import com.example.jetpack_demos.databinding.FragmentDest8LoginBinding
import kotlin.random.Random


class Dest8LoginFragment : BaseFragment<FragmentDest8LoginBinding>() {

    override fun layout() = R.layout.fragment_dest8_login

    val data = Random.nextInt().toString()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mbtRegister.setOnClickListener(this)
        binding.tvData.text = data
    }


    override fun onClick(v: View) {
        navController.navigate(Dest8LoginFragmentDirections.loginToRegister())
    }
}