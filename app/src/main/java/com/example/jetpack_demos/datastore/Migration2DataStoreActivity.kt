package com.example.jetpack_demos.datastore

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.lifecycleScope
import com.example.jetpack_demos.R
import kotlinx.coroutines.launch

/**
 *  从SharedPreference迁移到datastore
 */
class Migration2DataStoreActivity : AppCompatActivity() {

    private val KEY_NAME = intPreferencesKey("name")
    private val KEY_AGE = intPreferencesKey("age")
    private val KEY_SEX = intPreferencesKey("sex")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        val sharedPreferences = getSharedPreferences(spName, Context.MODE_PRIVATE)
        sharedPreferences.edit {
            putString("name", "jsonYang")
            putInt("age", 30)
            putBoolean("sex", true)
        }


        lifecycleScope.launch {
            userSp.data.collect { sp ->
                println("name=${sp[KEY_NAME]}")
                println("age=${sp[KEY_AGE]}")
                //当忽略sex迁移时，sex为null
                println("sex=${sp[KEY_SEX]}")
            }
        }

        //测试将sp迁移到proto datastore
        val sharedPreferences2 = getSharedPreferences(spSettingName, Context.MODE_PRIVATE)
        sharedPreferences2.edit {
            putInt(KEY_COUNT.name, 100100)
        }

        lifecycleScope.launch {
            settings.data.collect { settings ->
                println("mysettings :${settings}  -----end")
            }
        }
    }
}