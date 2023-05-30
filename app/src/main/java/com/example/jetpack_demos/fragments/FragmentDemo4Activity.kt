package com.example.jetpack_demos.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.jetpack_demos.R

/**
 * 自定义fragment factory
 */
class FragmentDemo4Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = MyFragmentFactory(UserReopository())
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment4_demo)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add<Fragment4CustomFactoryDemo>(R.id.fragmentContainerView)
                setReorderingAllowed(true)
                addToBackStack("A3")
            }
        }
    }
}