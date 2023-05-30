package com.example.jetpack_demos.fragments

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.jetpack_demos.R

class FragmentDemo6TransitionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment6_demo)

        findViewById<Button>(R.id.btTransition).setOnClickListener {
            supportFragmentManager.commit {
                replace<Fragment62Demo>(R.id.fragmentContainerView)
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }
    }
}