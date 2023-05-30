package com.example.jetpack_demos.datastore

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.lifecycleScope
import com.example.jetpack_demos.App
import com.example.jetpack_demos.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * datastore for SharedPreference
 * datastore for proto （使用protobuff，存储类型对象，类型安全）
 * datastore for serializable （使用kotlin序列化，存储类型对象）
 * 思考
 * 1.如何与viewmodel一起使用？
 *
 */
class ThreeDataStoreActivity : AppCompatActivity() {

    private val KEY_VERSION = intPreferencesKey("version")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        //sp
        val versionFlow = spDataStore.data.map { sp -> sp[KEY_VERSION] ?: 0 }

        lifecycleScope.launch {
            saveVersion()

            versionFlow.collectLatest {
                println("version=$it")
            }
        }

        //datastore with kotlin Serializable
        val userFlow = jsonDataStore.data
        lifecycleScope.launch {
            saveUser()

            userFlow.collectLatest { user ->
                println("user=$user")
            }
        }


        //datastore with google protobuff
        val settingsFlow = settingsDataStore.data
        lifecycleScope.launch {
            saveSettings()
            settingsFlow.collectLatest { settings ->
                println("settings=$settings")
            }
        }

//        runBlocking {}
        startService(Intent(this, MyService::class.java))

        lifecycleScope.launch {
            (applicationContext as App).moreProcessDataStore.data.collect {
                println("time :${it.exampleCounter}")
            }
        }
    }

    private suspend fun saveSettings() {
        settingsDataStore.updateData { settings ->
            return@updateData settings.toBuilder().setExampleCounter(100).build()
        }
    }

    private suspend fun saveUser() {
        jsonDataStore.updateData { user ->
            return@updateData user.copy(
                id = 1,
                name = "json",
                isVip = true
            )
        }
    }

    //sp保存值
    suspend fun saveVersion() {
        spDataStore.edit { sp ->
            sp[KEY_VERSION] = 100
        }
    }
}