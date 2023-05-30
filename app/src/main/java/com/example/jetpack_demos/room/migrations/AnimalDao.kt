package com.example.jetpack_demos.room.migrations

import androidx.room.Dao
import androidx.room.Query

@Dao
interface AnimalDao {
    @Query("select animals.id,animals.name,categories.id as categoryId,categories.name as categoryName from animals join categories on animals.category_id=categories.id where animals.id=:id")
    fun getAnimalDetail(id: Int): AnimalDetail
}