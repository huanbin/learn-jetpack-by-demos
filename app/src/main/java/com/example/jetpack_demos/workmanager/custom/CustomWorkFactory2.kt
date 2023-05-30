package com.example.jetpack_demos.workmanager.custom

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.jetpack_demos.workmanager.FilterWorker

/**
 * 自定义WorkerFactory,传递参数到Worker
 */
class CustomWorkFactory2(val name: String) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        println("CustomWorkFactory2:$workerClassName")
        println("CustomWorkFactory2 simple name:${WorkerWithParam2::class.java.simpleName}")
        when (workerClassName) {
            WorkerWithParam2::class.java.name ->
                return WorkerWithParam2(name, appContext, workerParameters)

            else ->
                return null
        }

    }
}