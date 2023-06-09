package com.example.jetpack_demos.view.pane

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.jetpack_demos.R
import com.example.jetpack_demos.databinding.FragmentSlidingPaneItemBinding

class SelectAnItemFragment : Fragment(R.layout.fragment_sliding_pane_item) {

    lateinit var binding: FragmentSlidingPaneItemBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSlidingPaneItemBinding.bind(view)
        binding.tvContent.text = arguments?.getString("title").toString() + "\n你好！"
    }
}