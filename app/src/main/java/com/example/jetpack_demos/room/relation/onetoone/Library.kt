package com.example.jetpack_demos.room.relation.onetoone

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Library(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val userOwnerId: Int
)
