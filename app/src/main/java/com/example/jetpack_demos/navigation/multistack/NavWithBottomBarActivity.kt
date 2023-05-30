package com.example.jetpack_demos.navigation.multistack

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.jetpack_demos.R
import com.example.jetpack_demos.databinding.ActivityNavWithBottomBarBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavWithBottomBarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavWithBottomBarBinding
    private lateinit var navController: NavController
    private var isEnable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavWithBottomBarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        isEnable = true
        val callBack = object : OnBackPressedCallback(isEnable) {
            override fun handleOnBackPressed() {
                Log.d("Hello", "handleOnBackPressed")
                finishAffinity()
            }
        }
        onBackPressedDispatcher.addCallback(this, callBack)

        val navView: BottomNavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_activity_nav_with_bottom_bar)
        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id in setOf(
                    R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
                )
            ) {
                navView.visibility = View.VISIBLE
            } else {
                navView.visibility = View.GONE
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.d("Hello", "onBackPressed")
    }
}