package com.example.jetpack_demos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.jetpack_demos.room.embed.Address
import com.example.jetpack_demos.room.embed.Student
import com.example.jetpack_demos.room.embed.StudentDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudentViewModel(val studentDao: StudentDao) : ViewModel() {
    class MyFactory(val studentDao: StudentDao) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return StudentViewModel(studentDao) as T
        }
    }

    val studentId = MutableStateFlow("-1")

    val _studentListId = MutableStateFlow(intArrayOf())
    val studentListId = _studentListId.asStateFlow()


    val studentFlow = studentId.flatMapLatest {
//        if (it == "-1") {
//            println("studentFlow -1")
//            return@flatMapLatest listOf(Student(-1, "未知学生", Address("未知", "未知"))).asFlow()
//        } else {
//            return@flatMapLatest studentDao.getStudentById(it.toInt())
//        }
        return@flatMapLatest studentDao.getStudentById(it.toInt())
    }
        .onStart {
            emit(Student(-1, "未知学生", Address("未知", "未知")))
        }
        //过滤空
        .filterNotNull()


    val studentListFlow = studentDao.getStudentList(studentListId.value)


    fun insert(vararg student: Student) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                studentDao.insert(*student)
            }
        }
    }

    fun update(vararg student: Student) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                studentDao.update(*student)
            }
        }
    }
}