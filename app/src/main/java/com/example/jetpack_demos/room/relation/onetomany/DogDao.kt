package com.example.jetpack_demos.room.relation.onetomany

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface DogDao {
    //事务，多个查询在事务上原子执行（确保多个操作同时成功）
    @Transaction
    @Query("select * from dog")
    fun getDog(): List<DogWithHourse>

    @Insert
    fun inserDogs(vararg dog: Dog)

    @Insert
    fun inserHourses(vararg hourse: Hourse)


    @Transaction
//    @Query("select * from hourse join dog on hourseOwnerId=dogId and hourseId=:hourseId")
    @Query("select * from hourse join dog on hourseOwnerId=dogId where hourseId=:hourseId")
    fun getHourseList(hourseId: Long): Map<Hourse, Dog>
}