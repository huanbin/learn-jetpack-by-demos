package com.example.jetpack_demos.room.relation.onetomany

import androidx.room.Embedded
import androidx.room.Relation

data class DogWithHourse(
    @Embedded
    val dog: Dog,
    //省略entity = Hourse::class 省略projection
//    @Relation(parentColumn = "dogId", entityColumn = "hourseOwnerId")
//    val hourse: List<Hourse>,
    @Relation(entity = Hourse::class, parentColumn = "dogId", entityColumn = "hourseOwnerId", projection = ["hourseId","name"])
    val myData: List<MyData>,
)
