package com.example.jetpack_demos.hilt

import javax.inject.Inject
import javax.inject.Singleton

//注意@Singleton单例与
@Singleton
class WebServiceImpl @Inject constructor() : WebService {
    override fun doService(): String {
        return "Hello ${this.javaClass.simpleName}"
    }
}