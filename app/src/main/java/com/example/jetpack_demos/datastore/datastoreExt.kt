package com.example.jetpack_demos.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.MultiProcessDataStoreFactory
import androidx.datastore.dataStore
import androidx.datastore.dataStoreFile
import androidx.datastore.migrations.SharedPreferencesMigration
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.jetpack_demos.MySettings

val fileName = "sp_datastore"
val fileNameJson = "user.json"

//扩展spDataStore属性，确保其在整个应用中是一个单例
val Context.spDataStore: DataStore<Preferences> by preferencesDataStore(name = fileName)
val Context.jsonDataStore: DataStore<User> by dataStore(
    fileName = fileNameJson,
    serializer = UserSerializer
)
val Context.settingsDataStore: DataStore<MySettings> by dataStore(
    fileName = "settings.pb",
    serializer = SettingsSerializer
)

fun Context.moreProcessDataStore() = MultiProcessDataStoreFactory.create(
    serializer = SettingsSerializer,
    produceFile = {
        //File(cacheDir.path,"myapp.pb")
        dataStoreFile("myapp.pb")
    }
)

//测试sp迁移到datastore
val spName = "user.sp"
val Context.userSp by preferencesDataStore(name = spName, produceMigrations = { context ->
    listOf(
        SharedPreferencesMigration(
            context,
            spName,
            //需要迁移的key
            keysToMigrate = setOf("name", "age")
        )
    )
})

//测试sp迁移到datastore proto
val pbName = "settings.pb"
val spSettingName = "settings.sp"
val KEY_COUNT = intPreferencesKey("count")
val Context.settings by dataStore<MySettings>(
    pbName,
    SettingsSerializer,
    produceMigrations = { context ->
        return@dataStore listOf(
            SharedPreferencesMigration(
                context = context,
                sharedPreferencesName = spSettingName,
                migrate = { sharedPreferencesView, mySettings ->
                    mySettings.toBuilder().setExampleCounter(
                        sharedPreferencesView.getInt(
                            KEY_COUNT.name, 0
                        )
                    )
                        .build()
                })
        )
    }
)
