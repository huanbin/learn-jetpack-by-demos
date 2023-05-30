package com.example.jetpack_demos.navigation

import android.os.Bundle
import android.view.View
import com.example.jetpack_demos.R
import com.example.jetpack_demos.databinding.FragmentDest9RegisterBinding

class Dest9RegisterFragment : BaseFragment<FragmentDest9RegisterBinding>() {

    override fun layout() = R.layout.fragment_dest9_register

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mbtRegisterOK.setOnClickListener(this)
        binding.mbtRegisterOK2.setOnClickListener(this)
        binding.mbtRegisterOK3.setOnClickListener(this)
        binding.mbtRegisterOK4.setOnClickListener(this)
        binding.mbtRegisterOK5.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.mbtRegisterOK -> {
                navController.navigate(R.id.main)
            }

            R.id.mbtRegisterOK4 -> {
                navController.navigate(R.id.main4)
            }

            R.id.mbtRegisterOK2 -> {
                navController.navigate(R.id.main2)
            }

            R.id.mbtRegisterOK3 -> {
                navController.navigate(R.id.main3)
            }
            R.id.mbtRegisterOK5 -> {
                navController.navigate(R.id.main5)
            }
        }
    }
}