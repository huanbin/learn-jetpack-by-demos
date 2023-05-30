package com.example.jetpack_demos.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import java.util.Arrays

val IMAGE_COMPRESS_URL_KEY="image_compress_url"
val IMAGE_UPLOAD_URL_KEY="image_upload_url"

class CompressWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        val imageUrl = inputData.getStringArray(IMAGE_COMPRESS_URL_KEY)
        println("do CompressWorker:${Arrays.toString(imageUrl)}")
        return Result.success(workDataOf(IMAGE_UPLOAD_URL_KEY to imageUrl))
    }
}