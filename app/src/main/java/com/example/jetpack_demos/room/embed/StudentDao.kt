package com.example.jetpack_demos.room.embed

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {

    @Insert
    fun insert(vararg student: Student)

    @Update
    fun update(vararg student: Student)

    @Query("SELECT * FROM Student WHERE id=:studentId")
    fun getStudentById(studentId: Int): Flow<Student>

    @Query("SELECT * FROM Student WHERE id in (:studentIds)")
    fun getStudentList(studentIds: IntArray): Flow<List<Student>>
}