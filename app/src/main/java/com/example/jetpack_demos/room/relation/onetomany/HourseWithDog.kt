package com.example.jetpack_demos.room.relation.onetomany

import androidx.room.Embedded

//涉及到双向关系？
data class HourseWithDog(
    @Embedded("hourse")
    val hourse: Hourse,
    val name: String,
    val id: Long
)
