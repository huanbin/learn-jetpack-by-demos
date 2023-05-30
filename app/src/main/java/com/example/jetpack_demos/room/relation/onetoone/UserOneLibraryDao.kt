package com.example.jetpack_demos.room.relation.onetoone

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface UserOneLibraryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserOne(vararg userOne: UserOne)

    @Insert
    fun insertLirary(vararg library: Library)

    @Transaction
    @Query("select * from userone")
    fun getUserOne(): List<UserOneLibrary>

    //标记方法为一个事务，一般用在Query上(执行多条查询)，如果Query本身执行的是insert、update、remove就没有效果，因为这些默认在一个事务中执行。
    // 一般2种情况下需要，查询大的结果集、返回带有relation的pojo类
    @Transaction
    @Query("select * from userone")
    fun getUserOne2(): List<UserOneLibraryName>
}