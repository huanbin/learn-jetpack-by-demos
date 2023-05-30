package com.example.jetpack_demos.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.jetpack_demos.room.relationships.Book

//标记实体类对应于数据库中的一张表
//如果修改了表的名称，导致sql中的表名不一致，怎么处理？？？
@Entity(tableName = "users")
//支持全文检索
//@Fts4
data class User(
    //标记实体类的字段为表的主键,autoGenerate为true表示需要SQLite自动生长主键（插入数据时，实体的实例中该字段值为0和null视为没有设置）
    @PrimaryKey(autoGenerate = true)
    //启用FTS，如果有配置主键，则必须配置列名为rowid
    //@ColumnInfo(name = "rowid")
    val uid: Int,
    //val rowid: Int = 0,
    //对类实体字段关联的表的列进行自定义配置
    //name（数据库的表中对应列的名称） 默认是类实体的字段名，这里可以自行配置数据库中对应的列名称
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?
)
