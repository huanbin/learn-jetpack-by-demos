package com.example.jetpack_demos.hilt

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpack_demos.App
import com.example.jetpack_demos.R
import javax.inject.Inject

/**
 * 使用dagger注入依赖
 */
class HiltDemo2Activity : AppCompatActivity() {

    @Inject
    lateinit var webService: WebService

    override fun onCreate(savedInstanceState: Bundle?) {

        (applicationContext as App).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hilt_demo2)

        val doService = webService.doService()
        Toast.makeText(this, "hello:$doService", Toast.LENGTH_SHORT).show()

        findViewById<Button>(R.id.btLogin).setOnClickListener {
            startActivity(Intent(this, LoginMainActivity::class.java))
        }
    }
}