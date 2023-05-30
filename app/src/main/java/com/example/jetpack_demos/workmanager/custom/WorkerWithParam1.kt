package com.example.jetpack_demos.workmanager.custom

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay

class WorkerWithParam1(private val count: Long, appContext: Context, params: WorkerParameters) :
    CoroutineWorker(
        appContext,
        params
    ) {


//    constructor(appContext: Context, params: WorkerParameters) : this("", appContext, params)

    override suspend fun doWork(): Result {
        println("wait......................")
        delay(5000)
        return Result.success(workDataOf("data" to "get data success,count=$count"))
    }
}