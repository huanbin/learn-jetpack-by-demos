package com.example.jetpack_demos.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jetpack_demos.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FragmentDemo13_2BottomSheetDialog : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_demo13_2, container, false)
        return view
    }

}