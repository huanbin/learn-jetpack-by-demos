package com.example.jetpack_demos.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.jetpack_demos.R


class Fragment10_2Demo : Fragment(R.layout.fragment_demo10_2) {

    val viewModel10Demo by activityViewModels<ViewModel10Demo>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView = view.findViewById<TextView>(R.id.tvData)
        viewModel10Demo.name.observe(viewLifecycleOwner) { newName: String ->
            textView.text = newName
        }
    }
}