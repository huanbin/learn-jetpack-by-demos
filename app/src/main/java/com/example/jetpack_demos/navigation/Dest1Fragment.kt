package com.example.jetpack_demos.navigation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.ActivityNavigatorExtras
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.navOptions
import com.example.jetpack_demos.R
import com.example.jetpack_demos.databinding.FragmentDest1Binding

/**
 *  NavDeepLinkRequest---->用于访问deeplink
 *  NavDeepLinkBuilder---->用于构建deeplink对象
 *
 * 1.navController.navigateUp()-----》向上导航，在跨越app导航时，与popBackStack行为表现不同，其它时候是一样的,比如一直显示向上导航按钮时（当toolbar与navcontroller配合时，默认首页不显示向上导航），一般是想自定义topbar的返回键时使用，处理返回导航
 *   navController.popBackStack()----》返回导航
 * 2.popUpTo从堆栈中弹出目的地到达指定目的地（堆栈中存在的已访问过的历史目的地id）
 * 3.popUpToInclusive表示是否弹出popUpTo指向的目的地
 * 4.save和restore保存和恢复返回栈中目的地状态（状态又包哪些内容？）
 * 5.拦截处理系统back事件
 * 6.enterAnim、exitAnim、popEnterAnim、popExitAnim分别是什么过渡动画？
 * 7.onSupportNavigateUp()与NavigationUI
 * 8.如何使用navController.previousBackStackEntry.savedStateHandle向上一个目的地传递数据
 * 9.每一次导航，返回返回栈，都会重新创建view，和4类似问题
 */
class Dest1Fragment : BaseFragment<FragmentDest1Binding>() {

    override fun layout(): Int = R.layout.fragment_dest1


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mbtWithParam.setOnClickListener(this)
        binding.mbtDialog.setOnClickListener(this)
        binding.mbtActivity.setOnClickListener(this)
        binding.mbtActivityWithParam.setOnClickListener(this)
        binding.mbtActivityWithIntent.setOnClickListener(this)
        binding.mbtActivityWithIntentAndData.setOnClickListener(this)
        binding.mbtLogin.setOnClickListener(this)
        binding.mbtProgram1.setOnClickListener(this)
        binding.mbtProgram2.setOnClickListener(this)
        binding.mbtProgram3.setOnClickListener(this)
        binding.mbtNestedGraphDest.setOnClickListener(this)
        binding.mbtModuleDest.setOnClickListener(this)
        binding.mbtConditionDest.setOnClickListener(this)
        binding.mbtAnimalDest.setOnClickListener(this)
        binding.mbtShareElementInFragment.setOnClickListener(this)
        binding.mbtShareElementInActivity.setOnClickListener(this)


        navController.currentBackStackEntry!!.lifecycle.addObserver( /* observer = */ object :
            LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                //Toast.makeText(context, "lifecyle is : ${event.name}", Toast.LENGTH_SHORT).show()
            }
        })
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.mbtWithParam -> {
//              val navDirections = Dest1FragmentDirections.mainToDest2()
                val navDirections =
                    Dest1FragmentDirections.mainToDest2(title = "第二个页面标题", number = 100)
                navController.navigate(navDirections)
            }

            R.id.mbtDialog -> {
//                val mainToDialog = Dest1FragmentDirections.mainToDialog(myarg = null)
                val mainToDialog =
                    Dest1FragmentDirections.mainToDialog(myarg = "DialogFragment使用场景")
                navController.navigate(mainToDialog)
            }

            R.id.mbtActivity -> {
                /*val mainToActivity = Dest1FragmentDirections.mainToActivity()
                navController.navigate(mainToActivity)*/
                navController.navigate(R.id.dest4Activity)
            }

            R.id.mbtActivityWithParam -> {
                /*val fromBundle = Dest5ParamActivityArgs.fromBundle(
                    bundleOf(
                        "param1" to "apple",
                        "price" to 120f
                    )
                )
                val bundle = fromBundle.toBundle()*/
                navController.navigate(
                    R.id.dest5ParamActivity,
                    bundleOf("param1" to "apple", "price" to 120f)
                )
            }

            R.id.mbtActivityWithIntent -> {
                navController.navigate(R.id.dest6Activity)
            }

            R.id.mbtActivityWithIntentAndData -> {
                navController.navigate(R.id.dest7Activity, bundleOf("userId" to "user_id123"))
            }

            R.id.mbtLogin -> {
                navController.navigate(R.id.login)
            }

            R.id.mbtRegister -> {
                Toast.makeText(context, "只能通过deeplink导航", Toast.LENGTH_SHORT).show()
            }

            //普通导航。以编程方式导航，会忽略xml中的所有配置
            R.id.mbtProgram1 -> {
                navController.navigate(
                    //action id或者destnation id
                    resId = R.id.mainToDest10,
                    //目的地所需的参数
                    args = bundleOf("dogName" to "xiaohei"),
                    //导航操作的特定可选项
                    navOptions = navOptions {
                        anim {
                            enter = androidx.fragment.R.animator.fragment_open_enter
                            exit = androidx.fragment.R.animator.fragment_close_exit
                        }
                    },
                    //传递给Navigator启用特有的行为，比如：FragmentNavigator添加共享元素，实现动画
                    navigatorExtras = null
                )
            }
            //deeplink，以编程方式导航,隐式deeplink
            R.id.mbtProgram2 -> {
//                val deepLink = "https://com.deeplink.demo?dogName=xiaoming"
                val deepLink = "hb://com.deeplink.demo/my?dogName=xiaoming"
                //navController.navigate(route = "")
                //第一种
//                val navDeepLinkRequest = NavDeepLinkRequest.Builder.fromUri(deepLink.toUri()).build()
//                navController.navigate(navDeepLinkRequest)
                //第二种
//                navController.navigate(deepLink.toUri())

                //这种不行，无法传递参数
                /*val navDeepLinkRequest =
                    NavDeepLinkRequest.Builder.fromAction("android.intent.action.MY_ACTION")
                        .build()
                navController.navigate(navDeepLinkRequest)*/

                //第三种
                /*val navDeepLinkRequest =
                    NavDeepLinkRequest.Builder.fromUri(deepLink.toUri())
                        .setAction("android.intent.action.MY_ACTION")
                        .build()
                navController.navigate(navDeepLinkRequest)*/

                //第四种
                val intent = Intent("android.intent.action.MY_ACTION")
                intent.data = deepLink.toUri()
                val navDeepLinkRequest = NavDeepLinkRequest(intent)
                navController.navigate(navDeepLinkRequest)

                //注意这是在第三方App中的Activity访问deeplink
                /*     val dogName = "xiaohuang"
                     val intent = Intent(Intent.ACTION_VIEW)
         //            intent.data="https://com.deeplink.demo?dogName=${dogName}".toUri()
         //            注意默认的path=/必须带上，最好在定义的时候添加path到uri中
         //            intent.data = "hb://com.deeplink.demo/?dogName=${dogName}".toUri()
                     intent.data = "hb://com.deeplink.demo/my?dogName=$dogName".toUri()
                     //最好加上（不加，需要注意系统返回键导航和向上导航区别）
                     intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                     startActivity(intent)*/
            }

            R.id.mbtProgram3->{
                val pendingIntent = NavDeepLinkBuilder(requireContext())
                    .setDestination(R.id.dest16HandleBackEventFragment)
                    .setGraph(R.navigation.nav_graph)
                    .createPendingIntent()
                pendingIntent.send()
            }

            /**
             * 导航---》嵌套导航图的目的地报错，目的地不可见
             * cannot be found from the current destination Destination
             */
            R.id.mbtNestedGraphDest -> {
                navController.navigate(R.id.mainToRegister)
            }

            /**
             * 导航---》嵌套导航图（依赖模块中）的目的地报错，目的地不可见
             * cannot be found from the current destination Destination
             */
            R.id.mbtModuleDest -> {
                navController.navigate(R.id.mainToBlank)
            }

            R.id.mbtConditionDest -> {
//                val mainToDest11 = Dest1FragmentDirections.mainToDest11(isForceLogin = true)
                val mainToDest11 = Dest1FragmentDirections.mainToDest11(isForceLogin = false)
                navController.navigate(mainToDest11)
            }

            /*fragment切换动画*/
            R.id.mbtAnimalDest -> {
                navController.navigate(R.id.mainToDest13)
            }

            R.id.mbtShareElementInFragment -> {
                val fragmentNavigatorExtras = FragmentNavigatorExtras(binding.iv to "imageView")

                navController.navigate(
                    R.id.mainToDest14,
                    null,
                    navOptions {
                        //这里设置，就是没有效果（在Dest14Fragment中设置有动画效果）
//                        sharedElementEnterTransition=TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
                    },
                    navigatorExtras = fragmentNavigatorExtras
                )
            }

            R.id.mbtShareElementInActivity -> {
                val activityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        requireActivity(),
                        binding.iv,
                        "imageView"
                    )
                val activityNavigatorExtras = ActivityNavigatorExtras(activityOptionsCompat)
                navController.navigate(
                    R.id.dest15ShareElementAnimalActivity,
                    null,
                    null,
                    activityNavigatorExtras
                )
            }
        }
    }
}