package com.example.jetpack_demos.room.view

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OfficeCode(
    @PrimaryKey
    val id: Long,
    val name: String,
    val code: String,
    val city: String,
    val status: Int = -1
)