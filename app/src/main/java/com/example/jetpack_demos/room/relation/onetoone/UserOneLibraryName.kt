package com.example.jetpack_demos.room.relation.onetoone

import androidx.room.Embedded
import androidx.room.Relation

data class UserOneLibraryName(
    @Embedded
    val userOne: UserOne,
    @Relation(
        //子类的entity，这个字段的数据都与之相关
        entity = Library::class,
        //主实体的主键列名称
        parentColumn = "userId",
        //子实体中引用主实体主键的列名称
        entityColumn = "userOwnerId",
        //指定哪个列从子实体中获取,一般从返回的类型推导，只有这种单个属性时，需要指定（就像绑定实体和表映射关系一样）
        projection = ["name"]
    )
    val libraryName: String
)
