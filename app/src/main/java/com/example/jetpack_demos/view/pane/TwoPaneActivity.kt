package com.example.jetpack_demos.view.pane

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.example.jetpack_demos.R
import com.example.jetpack_demos.databinding.ActivitySlidingPaneBinding

class TwoPaneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySlidingPaneBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val myAdapter = MyAdapter(List(12) { index -> "title item $index" }) { index, item ->
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<SelectAnItemFragment>(
                    R.id.detail_container, tag = null, args = bundleOf(
                        "title" to item
                    )
                )
                if (binding.slidingLayout.isOpen) {
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                }
            }
            binding.slidingLayout.openPane()
        }
        binding.recyclerView.adapter = myAdapter
        binding.slidingLayout.lockMode = SlidingPaneLayout.LOCK_MODE_UNLOCKED
    }
}