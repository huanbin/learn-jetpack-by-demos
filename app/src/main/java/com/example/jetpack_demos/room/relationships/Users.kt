package com.example.jetpack_demos.room.relationships

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class Users(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo
    val name: String
)