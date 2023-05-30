package com.example.jetpack_demos.navigation.multistack.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "text_dashboard=${Random.nextInt()}"
    }

    val text: LiveData<String> = _text
}