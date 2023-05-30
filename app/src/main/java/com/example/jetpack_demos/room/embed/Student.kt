package com.example.jetpack_demos.room.embed

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo
    val name: String,
    //prefix 用于fix，当@Embedded的ower类与带有@Embedded的属性类中有同样的名称时避免名称冲突。
    @Embedded(prefix = "prefix_")
    val address: Address
) {
    constructor(name: String, address: Address) : this(0, name, address)
}


