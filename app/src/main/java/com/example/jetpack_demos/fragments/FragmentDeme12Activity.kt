package com.example.jetpack_demos.fragments

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.jetpack_demos.R

/**
 * Fragment中的Toolbar
 *
 * App中的Toolbar(建立在Single Activity架构基础上)
 * 1.如果App的页面有一个统一的AppBar,可以将Toolbar放在Activity层作为ActionBar使用（必须做为ActionBar）
 * 2.如果App的页面具有特殊的自定义效果,需要控制比如AppBar的尺寸大小、位置、切换页面（fragment）时的动画、或者某个页面需要一个可折叠的Toolbar时，将Toolbar放在每一个Fragment页面上。
 */
class FragmentDeme12Activity : AppCompatActivity() {

    val viewModel by viewModels<ViewModel10Demo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment12_demo)

        supportFragmentManager.setFragmentResultListener(
            "Fragment12Demo",
            this
        ) { requestKey, result ->
            supportFragmentManager.commit {
                replace<Fragment12_2Demo>(R.id.fragmentContainerView)
                setReorderingAllowed(true)
                addToBackStack("fragment12")
            }
        }

        supportFragmentManager.setFragmentResultListener("exit", this) { requestKey, result ->
            supportFragmentManager.popBackStack()
        }
    }
}