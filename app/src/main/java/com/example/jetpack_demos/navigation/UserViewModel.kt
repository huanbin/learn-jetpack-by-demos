package com.example.jetpack_demos.navigation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    val user = MutableLiveData<User>(null)
}