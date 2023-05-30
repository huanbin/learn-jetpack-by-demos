package com.example.jetpack_demos.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.jetpack_demos.R
import com.example.jetpack_demos.databinding.ActivityNavMainBinding

//导航组件
class NavMainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNavMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_nav_main)
        binding = ActivityNavMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //正确获取NavController的方式
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        //设置actionbar与导航关联
        appBarConfiguration = AppBarConfiguration(
            //顶级目的地，在这些目的地，向上按键不会出现
            topLevelDestinationIds = setOf(R.id.dest1Fragment),
            //navigateUp的默认行为返回false（navigateUp导航失败）时调用
            fallbackOnNavigateUpListener = {
                false
            }
        )
//          当做ActionBar使用
//        setSupportActionBar(binding.toolbar)
//        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        println("intent=${intent}")
    }
//      当做ActionBar使用时
//    override fun onSupportNavigateUp(): Boolean {
//        return NavigationUI.navigateUp(navController, appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }
}