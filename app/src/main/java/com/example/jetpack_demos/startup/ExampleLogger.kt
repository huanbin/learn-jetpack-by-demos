package com.example.jetpack_demos.startup

import androidx.work.WorkManager

class ExampleLogger(val workerManager: WorkManager) {
    init {
        println("ExampleLogger init...")
    }
}