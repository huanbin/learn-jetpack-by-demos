package com.example.jetpack_demos.room.view

import androidx.room.Dao
import androidx.room.Query

@Dao
interface EmployDao {

    @Query("select * from employdetail where id=:id")
    fun getEmployDetail(id: Long): EmployDetail
}