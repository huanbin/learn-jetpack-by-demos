package com.example.jetpack_demos.room.relation.onetomany

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Hourse(
    @PrimaryKey(autoGenerate = true)
    val hourseId:Long,

    val  name:String,

    val hourseOwnerId:Long
)
