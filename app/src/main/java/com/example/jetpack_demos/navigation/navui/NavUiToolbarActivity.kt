package com.example.jetpack_demos.navigation.navui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.jetpack_demos.R
import com.example.jetpack_demos.databinding.ActivityNavUiToolbarBinding

/**
 * Toolbar与DrawerLayout一起
 */
class NavUiToolbarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNavUiToolbarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //用FragmentContainerView时
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
//        val navController = navHostFragment.navController
        /*用fragment时*/
        val navController = findNavController(R.id.navHost)
        //不要这么用，有bug
        //setSupportActionBar(binding.toolbar)
        //单独toolbar用法
        binding.toolbar.setupWithNavController(
            navController, AppBarConfiguration(
                navController.graph,
                drawerLayout = binding.drawerLayout
            )
        )
        /*          //ActionBar
                setSupportActionBar(binding.toolbar)
             //此时应当setupActionBarWithNavController而不是Toolbar的setupWithNavController
               //默认是导航图中start目的地作为top level
       //        setupActionBarWithNavController(navController, AppBarConfiguration(setOf(R.id.nav_graph_navui)))
               setupActionBarWithNavController(
                   navController,
                   AppBarConfiguration(
       //                topLevelDestinationIds = setOf(
       //                    R.id.addFragment,
       //                    R.id.settingsFragment,
       //                    R.id.navUITopAppBarFragment
       //                ),
                       navController.graph,
                       drawerLayout = binding.drawerLayout
                   )
               )
       */
        binding.navView.setupWithNavController(navController)
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val navController = findNavController(R.id.navHost)
//        return NavigationUI.onNavDestinationSelected(
//            item,
//            navController
//        ) || super.onOptionsItemSelected(item)
//    }
}