package com.example.jetpack_demos.room.migrations

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Categories(
    @PrimaryKey
    val id: Int,
    val name: String,
)
