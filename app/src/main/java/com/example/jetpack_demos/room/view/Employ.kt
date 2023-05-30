package com.example.jetpack_demos.room.view

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Employ(
    @PrimaryKey
    val id: Long, val name: String, val officeCode: String, val salary: Long
)
