package com.example.jetpack_demos.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.example.jetpack_demos.R
import com.example.jetpack_demos.databinding.FragmentDialogBinding

class Dest3DialogFragment : DialogFragment() {

    lateinit var binding: FragmentDialogBinding

    val args by navArgs<Dest3DialogFragmentArgs>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //设置该fragment显示在dialog内（不是附加到Activity上），必须在onCreate中设置，否则没有效果
        showsDialog = true
        //不能取消
        isCancelable = false

        println("myarg=${args.myarg}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_dialog, container, false)
        return binding.root
    }

    lateinit var data: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        data = args.myarg ?: "123"
        binding.tvContent.text = "data:$data"
        requireDialog().setTitle("dialog title=$data")


        binding.btCancel.setOnClickListener {
            Toast.makeText(context, "cancel", Toast.LENGTH_SHORT).show()
            dismiss()
        }
        binding.btConfirm.setOnClickListener {
            Toast.makeText(context, "confirm", Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }
}