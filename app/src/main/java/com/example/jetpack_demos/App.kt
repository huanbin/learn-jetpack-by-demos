package com.example.jetpack_demos

import android.app.Application
import androidx.startup.AppInitializer
import com.example.jetpack_demos.datastore.moreProcessDataStore
import com.example.jetpack_demos.hilt.DaggerAppComponent
import com.example.jetpack_demos.startup.ExampleLoggerInitializer
import dagger.hilt.android.HiltAndroidApp

/**
 * Configuration.Provider实现了按需初始化WorkManager
 */
//class App : Application(), Configuration.Provider {
@HiltAndroidApp
class App : Application() {

    companion object {

        lateinit var app: App

        fun getInstance(): App {
            return app
        }
    }
    /*    override fun getWorkManagerConfiguration(): Configuration {
    //        val delegatingWorkerFactory = DelegatingWorkerFactory()
    //        delegatingWorkerFactory.addFactory(CustomWorkFactory1(100))
    //        delegatingWorkerFactory.addFactory(CustomWorkFactory2("world"))
            return Configuration.Builder()
                .setMinimumLoggingLevel(Log.VERBOSE)
    //                单个Worker类
                .setWorkerFactory(CustomWorkFactory2("nini"))
    //                多个worker类
    //            .setWorkerFactory(delegatingWorkerFactory)
                .build()
        }*/

    override fun onCreate() {
        super.onCreate()
        val appInitializer = AppInitializer.getInstance(applicationContext)
        appInitializer.initializeComponent(ExampleLoggerInitializer::class.java)
//        appInitializer.initializeComponent(MyWorkerInitializer::class.java)
        app = this
    }

    val appComponent= DaggerAppComponent.create()
//    val loginComponent= DaggerLoginComponent.create()
//    val test=DaggerTestComponent.

    val moreProcessDataStore=moreProcessDataStore()
}