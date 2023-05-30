package com.example.jetpack_demos.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.jetpack_demos.R

/**
 * 推迟过渡动画（转场动画）
 *
 * 其实推迟，本质上是推迟了Fragment事务的执行
 */
class FragmentDemo9RecycleViewActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fragment9_demo)

        val fragment91Demo = Fragment91Demo { item: MyImgItem, view: View ->
            supportFragmentManager.commit {
                addSharedElement(view, view.transitionName)
                replace<Fragment92Demo>(
                    R.id.fragmentContainerView,
                    args = bundleOf("item" to item, "name" to view.transitionName)
                )
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }

        supportFragmentManager.commit {
            //add<Fragment91Demo>(R.id.fragmentContainerView)
            add(R.id.fragmentContainerView, fragment91Demo)
            setReorderingAllowed(true)
        }
    }
}