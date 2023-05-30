package com.example.jetpack_demos.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.jetpack_demos.R
import com.google.android.material.appbar.MaterialToolbar

class Fragment12_2Demo : Fragment(R.layout.fragment_demo12_2) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.inflateMenu(R.menu.toolbar_menu_activity)

        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            parentFragmentManager.setFragmentResult("exit", bundleOf())
        }
    }

}