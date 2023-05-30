package com.example.jetpack_demos.room.relationships

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsersDao {

    @Insert
    fun insertUsers(vararg users: Users)

    //drop\truncate怎么执行
    //("TRUNCATE table users")

}