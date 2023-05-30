package com.example.jetpack_demos.room.migrations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class Song(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    @ColumnInfo("title", defaultValue = "")
    val addTitle: String
)
