package com.example.jetpack_demos.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import kotlin.random.Random

class PeriodWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(
    appContext,
    params
) {

    @Volatile
    var count = 0

    override suspend fun doWork(): Result {
        delay(Random.nextLong(10) * 1000L)
        println("doWork:$count")
        count++
        return Result.success()
    }
}