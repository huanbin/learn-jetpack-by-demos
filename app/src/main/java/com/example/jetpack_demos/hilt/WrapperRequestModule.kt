package com.example.jetpack_demos.hilt

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@InstallIn(ActivityComponent::class)
@Module
class WrapperRequestModule {

    /**
     * 一般用于提供第三方库中的类作为依赖、或者某些类需要build构造器方式创建对象
     * 注入Context
     */
    @Provides
//    fun provideRequest(@ApplicationContext context: Context):WrapperRequest{
    fun provideRequest(@ActivityContext context: Context): WrapperRequest {
        println("Hello::" + context::class.java.simpleName)
        return WrapperRequest.RequestBuilder()
            .url("http://www.baidu.com")
            .method("post")
            .data(hashMapOf("a" to "home", "c" to "list"))
            .build()
    }
}