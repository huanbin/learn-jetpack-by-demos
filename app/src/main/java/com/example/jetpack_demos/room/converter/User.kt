package com.example.jetpack_demos.room.converter

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class User(
    @PrimaryKey
    val id: Long,
    val name: String,
    val birthDay: Date
)