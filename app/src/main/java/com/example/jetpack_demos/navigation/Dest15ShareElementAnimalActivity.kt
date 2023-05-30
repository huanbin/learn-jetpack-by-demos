package com.example.jetpack_demos.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ActivityNavigator
import com.example.jetpack_demos.R

/**
 * activity共享元素国度动画
 */
class Dest15ShareElementAnimalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dest15_share_element_animal)
    }


    override fun finish() {
        super.finish()
        //设置popup动画
        ActivityNavigator.applyPopAnimationsToPendingTransition(this)
    }
}