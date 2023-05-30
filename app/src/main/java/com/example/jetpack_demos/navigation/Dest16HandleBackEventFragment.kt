package com.example.jetpack_demos.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.jetpack_demos.R

/**
 * 处理系统back button返回事件
 *
 * ToolBar的向上导航事件
 * 1.首先得思考是否该使用NavigationUI，一般是App内整体Toolbar功能一致，页面没有特殊功能实现，比如某个页面，需要拦截向上导航事件，显示一个Dialog提示用户确认操作。此时就不太合适，但也有办法实现。
 * 2.具体实现方法：在Activity中监听导航事件，根据目的地的变化，动态的显示和隐藏Activity的ToolBar，然后在Fragment页面实现自己的Toolbar，并监听向上导航点击事件，手动处理导航逻辑。
 *
 */
class Dest16HandleBackEventFragment : Fragment() {

    lateinit var onBackPressed: OnBackPressedCallback
    var enabled = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dest16_handle_back_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //拦截系统返回键事件
        enabled=true

        onBackPressed = object : OnBackPressedCallback(enabled) {
            override fun handleOnBackPressed() {
                val alertDialog = AlertDialog.Builder(requireContext())
                    .setTitle("确认退出吗？")
                    .setNeutralButton("取消") { dialog, which ->
                        dialog.dismiss()
                    }
                    .setPositiveButton("确定") { dialog, which ->
                        dialog.dismiss()
                        findNavController().popBackStack()
                    }
                    .create()
                alertDialog.show()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressed
        )
    }
}