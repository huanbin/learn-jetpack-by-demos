package com.example.jetpack_demos.room.relation.onetoone

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserOne(
    @PrimaryKey(autoGenerate = true)
    val userId: Int,
    val name: String
)
