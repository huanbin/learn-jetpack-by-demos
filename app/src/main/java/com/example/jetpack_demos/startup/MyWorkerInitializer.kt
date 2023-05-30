package com.example.jetpack_demos.startup

import android.content.Context
import androidx.startup.Initializer
import androidx.work.Configuration
import androidx.work.WorkManager

/**
 * 手动初始化WorkManager
 * 一般是在需要自定义配置的情况下需要手动初始化
 * 步骤：
 * 1.禁用自动初始化
 * 2.实现Initializer，调用WorkManager的initialize方法
 */
class MyWorkerInitializer : Initializer<WorkManager> {
    override fun create(context: Context): WorkManager {
        println("MyWorkerInitializer create...")
        val configuration = Configuration.Builder()
            .setDefaultProcessName("MyWorkerInitializer")
            .build()
        WorkManager.initialize(context, configuration)
        return WorkManager.getInstance(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        println("MyWorkerInitializer dependencies...")
        return emptyList()
    }
}