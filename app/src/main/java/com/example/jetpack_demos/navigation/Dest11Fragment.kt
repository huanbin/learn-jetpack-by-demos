package com.example.jetpack_demos.navigation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavOptions
import com.example.jetpack_demos.R
import com.example.jetpack_demos.databinding.FragmentDest11Binding
import kotlin.random.Random

/**
 * 条件导航，暂时没有实现下面的2种情景
 * 1.不如说处理强制登录的，不登录则退出App
 * 2.跳转登录，但是用户可以选择不登录，继续使用App
 */
class Dest11Fragment : BaseFragment<FragmentDest11Binding>() {

    override fun layout(): Int = R.layout.fragment_dest11

    val userViewModel by activityViewModels<UserViewModel>()

    val data = Random.nextInt().toString()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        * 强制登录，不登录则直接退出App
        * */
        if (arguments?.getBoolean("isForceLogin") == true) {
            //没有登录，跳转登录页面
            userViewModel.user.observe(viewLifecycleOwner) {
                if (it == null) {
                    val dest11ToDest12Selection =
                        Dest11FragmentDirections.dest11ToDest12Selection(isForceLogin = true)
                    navController.navigate(dest11ToDest12Selection)
                }
            }
            binding.tvData.text = "用户$data:已经登录"
        } else {
//            可以不登录
            val isLogin = navController.currentBackStackEntry?.savedStateHandle?.get<Boolean>(
                Dest12SelectionFragment.LOGIN_SUCCESSFUL
            )
            if (isLogin == null) {
                val dest11ToDest12Selection =
                    Dest11FragmentDirections.dest11ToDest12Selection(isForceLogin = false)
                navController.navigate(dest11ToDest12Selection)
            }


            navController.currentBackStackEntry?.savedStateHandle?.getLiveData<Boolean>(
                Dest12SelectionFragment.LOGIN_SUCCESSFUL
            )?.observe(viewLifecycleOwner) { isLogin ->
                Toast.makeText(context, "isLogin=$isLogin", Toast.LENGTH_SHORT).show()
                if (!isLogin) {//如果还是不登录，可以把当前页面pop掉
                    val startDestination = navController.graph.startDestinationId
                    val navOptions = NavOptions.Builder().setPopUpTo(startDestination, true).build()
                    navController.navigate(startDestination, null, navOptions)
                }else{
                    binding.tvData.text = "用户$data:已经登录"
                }
            }
        }
    }


    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}