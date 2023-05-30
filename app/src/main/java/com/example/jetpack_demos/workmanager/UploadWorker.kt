package com.example.jetpack_demos.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.Arrays

class UploadWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        val imageUrl = inputData.getStringArray(IMAGE_UPLOAD_URL_KEY)
        println("do UploadWorker:${Arrays.toString(imageUrl)}")
        return Result.success()
    }
}