package com.example.jetpack_demos.hilt

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [LoginModule::class,WebServiceModule::class])
interface AppComponent {
    //dagger的注入
    fun inject(activity: HiltDemo2Activity)

    fun loginComponent():LoginComponent.Factory
}