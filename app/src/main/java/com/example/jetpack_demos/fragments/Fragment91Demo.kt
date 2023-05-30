package com.example.jetpack_demos.fragments

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import androidx.transition.TransitionSet
import com.example.jetpack_demos.R

//最小的fragment
class Fragment91Demo(val clickedItem: (item: MyImgItem, view: View) -> Unit) :
    Fragment(R.layout.fragment_demo91) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //添加一个退出过渡效果
        exitTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.grid_exit_transition)

        postponeEnterTransition()

        val vm by viewModels<ViewModel9Demo>()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.HORIZONTAL
            )
        )
        val imageAdapter = MyImageAdapter(
            onItemClick = { item: MyImgItem, view: View ->
                (exitTransition as TransitionSet).excludeTarget(view, true)
                clickedItem(item, view)
            }
        )
        recyclerView.adapter = imageAdapter
        vm.datas.observe(viewLifecycleOwner) { items ->

            imageAdapter.submitList(items)

            (view.parent as? ViewGroup)?.doOnPreDraw {
                startPostponedEnterTransition()
            }
        }
    }
}

