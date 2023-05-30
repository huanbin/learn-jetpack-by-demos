package com.example.jetpack_demos.datastore

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.jetpack_demos.App
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MyService : Service() {

    val scope = CoroutineScope(Dispatchers.IO)


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        scope.launch {
            while (isActive) {
                (applicationContext as App).moreProcessDataStore.updateData { settingsData ->
                    settingsData.toBuilder()
                        .setExampleCounter(System.currentTimeMillis().toInt())
                        .build()
                }
                delay(1000)
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}