package com.example.jetpack_demos.splashscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel: ViewModel() {

    var isReady=MutableLiveData(false)


    fun initDatas(){
        viewModelScope.launch {
            delay(300)
            println("init data ok！")
            isReady.value=true
        }
    }
}