package com.example.jetpack_demos.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModel10Demo : ViewModel() {

    private val _name: MutableLiveData<String> = MutableLiveData("xiaolizi")

    val name: LiveData<String> = _name

    fun updateName(newName: String) {
        _name.value = newName
    }

}