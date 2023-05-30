package com.example.jetpack_demos.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class WebServiceModule {

    @Binds
    abstract fun getWebServiceImpl(webServiceImpl: WebServiceImpl): WebService

}