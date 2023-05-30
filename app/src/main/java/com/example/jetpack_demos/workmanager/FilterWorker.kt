package com.example.jetpack_demos.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlin.random.Random

class FilterWorker(val context: Context, val workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    companion object {
        var isFirst = true
    }

    override fun doWork(): Result {
        val imageUrl = inputData.getString(IMAGE_URL_KEY)
        println("do FilterWorker:$imageUrl")
        //模拟并行执行的filter，如果其中一个失败，整个任务失败（不会执行接下来的任务），默认Task是串行的，filter
//        if (imageUrl=="$IMAGE_URL-2") {
//            return Result.failure()
//        }
        setProgressAsync(workDataOf("progress" to 20))
        Thread.sleep(Random.nextInt(10) * 1000L)
        setProgressAsync(workDataOf("progress" to 40))
        Thread.sleep(Random.nextInt(10) * 1000L)
        setProgressAsync(workDataOf("progress" to 80))
        Thread.sleep(Random.nextInt(10) * 1000L)

        //模拟重试机制
        if (imageUrl == "$IMAGE_URL-2" && isFirst) {
            isFirst = false
            return Result.retry()
        }
        setProgressAsync(workDataOf("progress" to 100))
        return Result.success(workDataOf(IMAGE_COMPRESS_URL_KEY to imageUrl))
    }


    override fun onStopped() {
        super.onStopped()
        println("onStopped")
    }
}