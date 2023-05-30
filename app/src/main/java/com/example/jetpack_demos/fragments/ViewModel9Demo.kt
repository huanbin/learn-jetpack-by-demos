package com.example.jetpack_demos.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ViewModel9Demo : ViewModel() {

    val datas = MutableLiveData<List<MyImgItem>>()

    val imgUrl = listOf(
        "https://lh1.hetaousercontent.com/img/6fa592184d4aefd2.jpg?thumbnail=true",
        "https://lh1.hetaousercontent.com/img/097b7bb5a7b86719.jpg?thumbnail=true",
        "https://lh1.hetaousercontent.com/img/b1eaa2ace7032e81.jpg?thumbnail=true",
        "https://lh1.hetaousercontent.com/img/85eb963fae3a044b.jpg?thumbnail=true",
        "https://lh1.hetaousercontent.com/img/68c5e00d558c57ed.jpg?thumbnail=true",
        "https://lh1.hetaousercontent.com/img/6fa592184d4aefd2.jpg?thumbnail=true",
        "https://lh1.hetaousercontent.com/img/097b7bb5a7b86719.jpg?thumbnail=true",
        "https://lh1.hetaousercontent.com/img/b1eaa2ace7032e81.jpg?thumbnail=true",
        "https://lh1.hetaousercontent.com/img/b1eaa2ace7032e81.jpg?thumbnail=true",
        "https://lh1.hetaousercontent.com/img/85eb963fae3a044b.jpg?thumbnail=true",
        "https://lh1.hetaousercontent.com/img/68c5e00d558c57ed.jpg?thumbnail=true",
        "https://lh1.hetaousercontent.com/img/b1eaa2ace7032e81.jpg?thumbnail=true",
    )

    init {
        viewModelScope.launch(Dispatchers.IO) {
            delay(300)
            datas.postValue(buildList {
                repeat(12) { index ->
                    add(MyImgItem(index.toString(), imgUrl[index]))
                }
            })
        }
    }

}