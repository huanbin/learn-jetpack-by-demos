package com.example.jetpack_demos.fragments

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.jetpack_demos.R

/**
 * 共享数据:通过提供适当的Scope给ViewModleProvider，获取相同ViewModel实例(ViewModel内部持有数据)，达到数据共享的目的.
 * 1.Activity与Fragment、Fragment与Fragmen之间交互
 * 2.父Fragment与子Fragment之间交互
 *
 * 一般来自本地或者server repo的数据,都会存储在ViewModel中,便于逃离配置变更,自动保存数据,这种场景下使用ViewModel共享数据即可
 * 对于一次性数据,比如说Fragment中的点击事件,此时应该使用Fragment Result Api更合适
 *
 */
class FragmentDeme10Activity : AppCompatActivity() {

    val viewModel by viewModels<ViewModel10Demo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment10_demo)

        supportFragmentManager.setFragmentResultListener(
            "requestKey1",
            this
        ) { requestKey, result ->
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<Fragment10_2Demo>(R.id.fragmentContainerView)
                addToBackStack("demo10")
            }
        }
    }
}