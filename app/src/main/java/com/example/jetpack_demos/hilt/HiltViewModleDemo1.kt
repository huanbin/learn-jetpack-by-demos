package com.example.jetpack_demos.hilt

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 为ViewModel提供绑定
 * 参数webService
 */
@HiltViewModel
class HiltViewModleDemo1 @Inject constructor(private val webService: WebService) : ViewModel() {

    val msg = MutableLiveData<String>("")

    fun getMessage() {
        viewModelScope.launch(Dispatchers.IO) {
            val doService = webService.doService()
            msg.postValue(doService)
        }
    }

    fun getMessage2(webService: WebService) {
        viewModelScope.launch(Dispatchers.IO) {
            val doService = webService.doService()
            msg.postValue(doService)
        }
    }
}