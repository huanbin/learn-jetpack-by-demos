package com.example.jetpack_demos.navigation.dsl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavType
import androidx.navigation.createGraph
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.fragment
import androidx.navigation.navigation
import com.example.jetpack_demos.R
import com.example.jetpack_demos.databinding.ActivityNavDslactivityBinding

class NavDSLActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityNavDslactivityBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_nav_dslactivity)

        val navController =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView)!!
                .findNavController()

        val navGraph = navController.createGraph(startDestination = "home", "homeRouteGraph") {
            fragment<ItemFragment>("home") {
                label = "首页列表"
            }
            //fragment目的地
            fragment<DetailFragment>("detail/{id}?search={search}") {
                label = "detail $id"
                argument("id") {
                    type = NavType.StringType
                }
                argument("search") {
                    type = SearchParametersType
                    defaultValue=SearchParameters("json", listOf("a","ab"))
                }
                /*
                * deeplink
                * */
                deepLink {
                    uriPattern = "http://www.example.com/plants/?id={id}&search={search}"
                    action = "android.intent.action.MY_ACTION"
                    mimeType = "image/*"
                }
            }
            //activity目的地
            //activity()

            //nested nav graph嵌套导航图目的地
            navigation(startDestination = "login","loginRoute"){
                fragment<LoginFragment>("login")
            }
        }

        navController.setGraph(navGraph,null)
    }
}