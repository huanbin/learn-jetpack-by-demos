package com.example.jetpack_demos.hilt

import dagger.Subcomponent


//dagger创建依赖图
//@Component
@Subcomponent
interface LoginComponent {


    @Subcomponent.Factory
    interface Factory {
        fun create(): LoginComponent
    }


    /**
     * 注意如果Login模块中有多个类（比如activity）需要注入依赖，必须声明多个函数
     * 函数名称可以为任意名称，但是Dagger中一般习惯命名为inject
     */
    //告诉Dagger，LoginMainActivity需要访问依赖图并请求注入依赖(在LoginMainActivity中主动请求注入依赖)
    fun inject(activity: LoginMainActivity)

    //比如login模块中的注册Activity
    //fun inject(activity: RegisterActivity)
}