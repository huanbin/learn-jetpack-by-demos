package com.example.jetpack_demos.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpack_demos.R
import com.example.jetpack_demos.room.migrations.db2

class MigrationMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_migration_main)
        val db2 = db2()
//        db2.openHelper.setWriteAheadLoggingEnabled(true)
        val version = db2.openHelper.readableDatabase.version
        println("database current version=$version")
        val animalDao = db2.animalDao()
        val animalDetail = animalDao.getAnimalDetail(2)
        println("animalDetail=$animalDetail")
//        db2.close()
    }
}