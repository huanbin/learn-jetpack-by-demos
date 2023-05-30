package com.example.jetpack_demos.fragments

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.jetpack_demos.R

/**
 * 推迟过渡动画（转场动画）
 *
 * 其实推迟，本质上是推迟了Fragment事务的执行
 */
class FragmentDemo8PostponeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment8_demo)
        findViewById<Button>(R.id.btTransitionShare).setOnClickListener {
            var imageView: ImageView =
                supportFragmentManager.findFragmentById(R.id.fragmentContainerView)!!.requireView()
                    .findViewById(R.id.imgView)
            supportFragmentManager.commit {
                setCustomAnimations(
                    R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out
                )
                addSharedElement(imageView, imageView.transitionName)
                replace<Fragment82Demo>(R.id.fragmentContainerView)
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }
    }
}