package com.example.jetpack_demos.fragments

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.jetpack_demos.R

class FragmentDemo7TransitionShareElementActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment7_demo)
        findViewById<Button>(R.id.btTransitionShare).setOnClickListener {
            var imageView: ImageView =
                supportFragmentManager.findFragmentById(R.id.fragmentContainerView)!!.requireView()
                    .findViewById(R.id.imgView)
            supportFragmentManager.commit {
                setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.slide_out
                )
                addSharedElement(imageView, "image")
                replace<Fragment72Demo>(R.id.fragmentContainerView)
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }
    }
}