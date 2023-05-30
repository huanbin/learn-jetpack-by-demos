package com.example.jetpack_demos.startup

import android.content.Context
import androidx.startup.Initializer
import androidx.work.WorkManager
import androidx.work.WorkManagerInitializer

class ExampleLoggerInitializer : Initializer<ExampleLogger> {
    override fun create(context: Context): ExampleLogger {
        println("ExampleLoggerInitializer create...")
        return ExampleLogger(WorkManager.getInstance(context))
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        println("ExampleLoggerInitializer dependencies...")
        return listOf(WorkManagerInitializer::class.java)
    }
}