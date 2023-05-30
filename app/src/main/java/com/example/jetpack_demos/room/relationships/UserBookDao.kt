package com.example.jetpack_demos.room.relationships

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface UserBookDao {
    @Query(
        "SELECT user.name AS userName, book.name AS bookName " +
                "FROM user, book " +
                "WHERE user.id = book.user_id"
    )
    fun loadUserAndBookNames(): List<UserBook>


    @Query(
        "SELECT * FROM user " +
                " JOIN book ON user.id = book.user_id"
    )
//    @Query(
//        "SELECT * FROM user, book " +
//                "WHERE user.id = book.user_id"
//    )
    fun loadUserAndBookNames2(): Map<Users, List<Book>>
}