package com.example.jetpack_demos.room.relationships

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val user_id: Int
)