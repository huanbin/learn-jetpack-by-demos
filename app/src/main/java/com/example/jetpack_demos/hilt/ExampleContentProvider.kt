package com.example.jetpack_demos.hilt

import com.example.jetpack_demos.App
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

/**
 * 为Hilt不支持的类，提供依赖,比如说
 */
class ExampleContentProvider @Inject constructor(){ //:ContentProvider{

    /**
     * EntryPoint和InstallIn必须一起使用
     *
     * 注意：一开始一直没有拿到WebService实例，是因为这里的InstallIn与提供WebService的module的InstallIn
     */
    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface ExampleContentProviderEntryPoint {
        fun webservice(): WebService
    }

    fun getMessage(): String {
        //EntryPoints.get()
        //获取实例
        val webServiceEntryPoint = EntryPointAccessors.fromApplication(
            App.getInstance(),
            ExampleContentProviderEntryPoint::class.java
        )
        val webservice = webServiceEntryPoint.webservice()
        val doService = webservice.doService()
        return "Custom EntryPoint$doService"
    }

}