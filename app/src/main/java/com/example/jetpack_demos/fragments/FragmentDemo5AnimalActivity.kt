package com.example.jetpack_demos.fragments

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.jetpack_demos.R

/**
 * fragment动画
 */
class FragmentDemo5AnimalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment5_demo)

        findViewById<Button>(R.id.btStartAnimal).setOnClickListener {
            go()
        }
    }

    private fun go() {
        supportFragmentManager.commit {
            //其实这里只是给Transaction成员变量赋值，所以只是针对当前事务内有效，并且对之前的fragment操作没有影响！
            setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
            replace<Fragment61Demo>(R.id.fragmentContainerView)
            setReorderingAllowed(true)
            addToBackStack("A1")
        }
    }
}