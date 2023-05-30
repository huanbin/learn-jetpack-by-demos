package com.example.jetpack_demos.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

//数据库访问对象，用于访问数据库数据的。
@Dao
interface UserDao {
    //标记为查询方法，并将结果集转换为方法的返回值
    @Query("SELECT *  FROM users")
    fun getAll(): List<User>

    //命名绑定参数，使用 :名称
    @Query("SELECT * FROM users WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query(
        "SELECT * FROM users WHERE first_name LIKE :first AND " +
                "last_name LIKE :last LIMIT 1"
    )
    fun findByName(first: String, last: String): User

    //插入一条或多条类实体表数据
    @Insert
    fun insertAll(vararg users: User)

    //删除指定类实体对象对应的表中的数据行row
    @Delete
    fun delete(user: User)
}