package com.example.jetpack_demos.room

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.jetpack_demos.viewmodel.StudentViewModel
import com.example.jetpack_demos.databinding.ActivityRoomAsyncMainBinding
import com.example.jetpack_demos.room.embed.Address
import com.example.jetpack_demos.room.embed.Student
import com.example.jetpack_demos.room.embed.StudentDao
import com.example.jetpack_demos.room.view.EmployDao
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoomAsyncMainActivity : AppCompatActivity() {

    lateinit var employDao: EmployDao
    lateinit var studentDao: StudentDao
    lateinit var etIdsOrName: TextInputEditText
    lateinit var tvSudent: MaterialTextView

    lateinit var viewModel: StudentViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val asyncMainBinding = ActivityRoomAsyncMainBinding.inflate(layoutInflater)
        setContentView(asyncMainBinding.root)
//        val roomMainBinding: ActivityRoomAsyncMainBinding =
//            DataBindingUtil.setContentView(/* activity = */ this, /* layoutId = */
//                R.layout.activity_room_async_main
//            )

        asyncMainBinding.lifecycleOwner = this

//        employDao = db().employDao()
//        studentDao = db().studentDao()
        viewModel = ViewModelProvider(
            this, StudentViewModel.MyFactory(studentDao)
        ).get(StudentViewModel::class.java)
        etIdsOrName = asyncMainBinding.etStudentId
        tvSudent = asyncMainBinding.tvStudent

        etIdsOrName.addTextChangedListener { text ->
            val input = if (text.toString().isNullOrBlank()) {
                "-1"
            } else {
                text.toString()
            }
            println("input=$input")
            viewModel.studentId.value = input
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.studentFlow.collectLatest {
                    println("studentFlow collect : $it")
                    tvSudent.text = it.toString()
                }

                viewModel.studentListFlow.collectLatest { students ->
                    println("studentListFlow collect : $students")

                    val buildString = buildString {
                        students.forEach {
                            append("${it.toString()}\n")
                        }
                    }
                    println("student list=$buildString")
                }
            }
        }

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                //databse view
                val employDetail = employDao.getEmployDetail(1)
                println("employDetail=$employDetail")
            }
        }
    }

    fun insertStudent(view: View) {
        if (!etIdsOrName.text.isNullOrBlank()) {
            val last = etIdsOrName.text.toString().last()
            viewModel.insert(
                Student(
                    etIdsOrName.text.toString(), Address("street-$last", "city-$last")
                )
            )
        }
    }

    fun updateStudentById(view: View) {
        if (!etIdsOrName.text.isNullOrBlank()) {
            val id = etIdsOrName.text.toString()
            if (id.isDigitsOnly()) {
                viewModel.update(
                    Student(
                        id.toInt(), "update-stu", Address("update street-$id", "update-city-$id")
                    )
                )
            }
        }
    }
}