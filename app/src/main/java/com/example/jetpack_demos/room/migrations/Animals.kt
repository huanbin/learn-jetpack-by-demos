package com.example.jetpack_demos.room.migrations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Animals(
    @PrimaryKey
    val id: Int,
    val name: String,
    @ColumnInfo(name = "category_id")
    val categoryId: Int,
)
