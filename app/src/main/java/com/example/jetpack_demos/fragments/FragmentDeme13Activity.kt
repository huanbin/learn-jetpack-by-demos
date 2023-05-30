package com.example.jetpack_demos.fragments

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpack_demos.R

/**
 * Fragment中的Dialog
 */
class FragmentDeme13Activity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment13_demo)

        findViewById<Button>(R.id.buttonDialog).setOnClickListener {
            FragmentDemo13_1CommonDialog().show(supportFragmentManager,"common")
        }

        findViewById<Button>(R.id.buttonBottomSheetDialog).setOnClickListener {
            val bottomSheetDialogFragment = FragmentDemo13_2BottomSheetDialog()
            bottomSheetDialogFragment.show(supportFragmentManager,"bottom_sheet")
        }
    }
}