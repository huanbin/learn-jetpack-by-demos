package com.example.jetpack_demos.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.jetpack_demos.R
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton

class Fragment12_1Demo : Fragment(R.layout.fragment_demo12_1) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.inflateMenu(R.menu.toolbar_menu_fragment)
        view.findViewById<MaterialButton>(R.id.btGo12).setOnClickListener {
            parentFragmentManager.setFragmentResult("Fragment12Demo", bundleOf())
        }
    }
}