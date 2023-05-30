package com.example.jetpack_demos.fragments

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.transition.TransitionInflater
import com.example.jetpack_demos.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//最小的fragment
class Fragment82Demo : Fragment(R.layout.fragment_demo82) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //进入fragment共享元素转场动画
        sharedElementEnterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(R.transition.share_image)

        //弹出fragment共享元素转场动画
//        sharedElementReturnTransition=TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.slide_top)
        postponeEnterTransition()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView = view.findViewById<ImageView>(R.id.img)

        lifecycleScope.launch(Dispatchers.IO) {
            delay(2000)
            withContext(Dispatchers.Main) {
                startPostponedEnterTransition()
                imageView.setImageResource(R.drawable.caidog)
            }
        }
    }
}