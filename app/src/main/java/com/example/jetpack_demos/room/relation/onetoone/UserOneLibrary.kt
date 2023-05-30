package com.example.jetpack_demos.room.relation.onetoone

import androidx.room.Embedded
import androidx.room.Relation

data class UserOneLibrary(
    @Embedded
    val parent: UserOne,
    //entity属性一般情况可以从返回值推导类型，但是返回类型可能是一个data class（不是Entity的class，但是属性集合是Entity class的属性集合的子集），可以查询返回指定的字段（字段少，不是所有的）。
    //parentColumn 设置为父实体主键的列名称
    //entityColumn 设置子实体中引用父实体主键的列的名称
    //在一对多或者多对多中，Relation注解的字段类型可以是List或者Set
    @Relation(entity = Library::class, parentColumn = "userId", entityColumn = "userOwnerId")
    val child: Library
)
