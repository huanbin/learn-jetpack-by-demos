package com.example.jetpack_demos.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.jetpack_demos.R

//最小的fragment
class Fragment1Demo:Fragment(R.layout.fragment_demo1){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arguments = requireArguments()
        Toast.makeText(
            requireContext(),
            "name is ${arguments.getString("name")}",
            Toast.LENGTH_SHORT
        ).show()
    }
}