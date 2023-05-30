package com.example.jetpack_demos.hilt

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpack_demos.App
import com.example.jetpack_demos.R
import javax.inject.Inject

class LoginMainActivity : AppCompatActivity() {

    lateinit var loginComponent: LoginComponent

    @Inject
    lateinit var webServiceImpl: WebService

    override fun onCreate(savedInstanceState: Bundle?) {

        // Creation of the login graph using the application graph
        loginComponent = (applicationContext as App)
            .appComponent.loginComponent().create()

        // Make Dagger instantiate @Inject fields in LoginActivity
        loginComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_main)

        Toast.makeText(this, "login --->${webServiceImpl.doService()}", Toast.LENGTH_SHORT).show()
    }
}