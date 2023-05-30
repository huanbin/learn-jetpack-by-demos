package com.example.jetpack_demos.room.relationships

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.jetpack_demos.room.relationships.Book

@Dao
interface BookDao {
    @Query("select * from book")
    fun getBooks(): List<Book>


    @Insert
    fun insertBooks(vararg book: Book)
}