package com.example.jetpack_demos.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore

//primaryKeys复合主键
//ignoredColumns忽略从父类继承来的字段
@Entity(primaryKeys = ["id", "country"], ignoredColumns = ["picture"])
data class People(
    @ColumnInfo
    var id: Long,
    @ColumnInfo
    var country: Long,
    @ColumnInfo
    var name: String,
    @ColumnInfo
    var age: Int,
    @ColumnInfo
    var address: String,
    //持久化，忽略该字段
    @Ignore
    var email: String
) : Human() {
    constructor() : this(0, 0, "", 0, "", "")
}