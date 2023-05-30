package com.example.jetpack_demos.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.jetpack_demos.R

class FragmentDemo13_1CommonDialog: DialogFragment(R.layout.fragment_demo13_1){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.buttonConfirm).setOnClickListener {
            dismiss()
        }
    }

}