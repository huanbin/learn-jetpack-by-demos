package com.example.jetpack_demos.navigation.multistack.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.jetpack_demos.R
import com.google.android.material.button.MaterialButton


class HomeSecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("Hello", "HomeSecondFragment user :${arguments?.getString("user")}")
        Log.d("Hello", "HomeSecondFragment sex :${arguments?.getInt("sex")}")

        view.findViewById<MaterialButton>(R.id.button6).setOnClickListener {

            findNavController().popBackStack(
                destinationId = R.id.homeSecondFragment,
                inclusive = true,
                saveState = true
            )
        }
    }
}