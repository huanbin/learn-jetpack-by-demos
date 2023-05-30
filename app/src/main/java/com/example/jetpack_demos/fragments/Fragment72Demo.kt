package com.example.jetpack_demos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.example.jetpack_demos.R

//最小的fragment
class Fragment72Demo : Fragment(R.layout.fragment_demo72){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //进入fragment共享元素转场动画
        sharedElementEnterTransition=TransitionInflater.from(requireContext()).inflateTransition(R.transition.share_image)

        //弹出fragment共享元素转场动画
//        sharedElementReturnTransition=TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.slide_top)
    }
}