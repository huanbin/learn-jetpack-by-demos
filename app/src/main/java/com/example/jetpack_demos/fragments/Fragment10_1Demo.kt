package com.example.jetpack_demos.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.jetpack_demos.R
import com.google.android.material.button.MaterialButton


class Fragment10_1Demo : Fragment(R.layout.fragment_demo10_1) {

    val viewModel10Demo by activityViewModels<ViewModel10Demo>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView = view.findViewById<TextView>(R.id.tvData)
        val inputView = view.findViewById<TextView>(R.id.textInput)
        viewModel10Demo.name.observe(viewLifecycleOwner) { newName: String ->
            textView.text = newName
            inputView.text = newName
        }
        val mbtSave = view.findViewById<MaterialButton>(R.id.mbtSave)
        mbtSave.setOnClickListener {
            val text = inputView.text
            if (TextUtils.isEmpty(text)) {
                return@setOnClickListener
            }
            viewModel10Demo.updateName(text.toString())
        }

        val mbtLook = view.findViewById<MaterialButton>(R.id.mbtLook)
        mbtLook.setOnClickListener {
            parentFragmentManager?.setFragmentResult("requestKey1", bundleOf())
        }
    }
}