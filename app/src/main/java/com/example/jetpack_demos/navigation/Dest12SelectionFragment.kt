package com.example.jetpack_demos.navigation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.navOptions
import com.example.jetpack_demos.R
import com.example.jetpack_demos.databinding.FragmentDest12Binding


class Dest12SelectionFragment : BaseFragment<FragmentDest12Binding>() {

    companion object {
        var LOGIN_SUCCESSFUL = "is_login_success"
    }

    val userViewModel by activityViewModels<UserViewModel>()

    lateinit var savedStateHandle: SavedStateHandle

    override fun layout() = R.layout.fragment_dest12

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mbtLoginSuccess1.setOnClickListener(this)
        binding.mbtLoginCancel1.setOnClickListener(this)
        binding.mbtLoginExitApp.setOnClickListener(this)
        binding.mbtLoginSuccess.setOnClickListener(this)

        val isForceLogin = arguments?.getBoolean("isForceLogin")

        if (isForceLogin == true) {
            //处理系统返回键事件
            binding.mbtLoginSuccess1.visibility = View.GONE
            binding.mbtLoginCancel1.visibility = View.GONE
            binding.mbtLoginExitApp.visibility = View.VISIBLE
            binding.mbtLoginSuccess.visibility = View.VISIBLE
        } else {
            binding.mbtLoginSuccess1.visibility = View.VISIBLE
            binding.mbtLoginCancel1.visibility = View.VISIBLE
            binding.mbtLoginExitApp.visibility = View.GONE
            binding.mbtLoginSuccess.visibility = View.GONE

            savedStateHandle = navController.previousBackStackEntry!!.savedStateHandle
            savedStateHandle[LOGIN_SUCCESSFUL] = false
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            /**
             * 登录成功，直接返回上一个页面
             */
            R.id.mbtLoginSuccess1 -> {
                //取消后直接去首页
                savedStateHandle[LOGIN_SUCCESSFUL] = true
                navController.popBackStack()
            }
            /**
             * 取消登录，返回首页
             */
            R.id.mbtLoginCancel1 -> {
                navController.popBackStack()
            }

            //直接退出App
            R.id.mbtLoginExitApp -> {
                navController.navigate(R.id.dest1Fragment, null, navOptions {
                    popUpTo(R.id.dest1Fragment) {
                        inclusive = true
                    }
                })
            }

            R.id.mbtLoginSuccess -> {
                //登录成功
                userViewModel.user.value = User(100, "json")

                val savedStateHandle =
                    navController.previousBackStackEntry!!.savedStateHandle
                savedStateHandle[LOGIN_SUCCESSFUL] = true

                navController.navigate(R.id.loginSuccessToDest11)
            }
        }
    }
}