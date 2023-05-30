package com.example.jetpack_demos.room.view

import androidx.room.DatabaseView

//数据库视图（封装查询，只支持select查询）
@DatabaseView(
    "SELECT employ.id, employ.name, employ.officeCode," +
            "officecode.name AS officeName FROM employ " +
            "INNER JOIN officecode ON employ.officeCode = officecode.id"
)
data class EmployDetail(
    val id: Long,
    val name: String,
    val officeCode: String,
    val officeName: String
)