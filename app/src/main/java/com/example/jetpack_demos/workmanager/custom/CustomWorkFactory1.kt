package com.example.jetpack_demos.workmanager.custom

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters

/**
 * 自定义WorkerFactory,传递参数到Worker
 */
class CustomWorkFactory1(val count: Long) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        println("CustomWorkFactory1:$workerClassName")
        println("CustomWorkFactory1 simple name:${WorkerWithParam1::class.java.simpleName}")
        when (workerClassName) {
            WorkerWithParam1::class.java.simpleName ->
                return WorkerWithParam1(count, appContext, workerParameters)

            else ->
                return null
        }

    }
}