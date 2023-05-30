package com.example.jetpack_demos.hilt

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

//配置更改重建Activity，实例是同一个。与ViewModel一样
@ActivityRetainedScoped
//@ActivityScoped
class Inject1Demo @Inject constructor() {
    fun log() {
        println("hello ${this::class.java.simpleName}")
    }
}