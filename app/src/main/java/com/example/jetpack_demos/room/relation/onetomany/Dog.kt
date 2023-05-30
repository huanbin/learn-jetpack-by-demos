package com.example.jetpack_demos.room.relation.onetomany

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dog(
    //如果给了id值，会不会生成？
    @PrimaryKey(autoGenerate = true)
    val dogId: Long,
    val name: String,
)


