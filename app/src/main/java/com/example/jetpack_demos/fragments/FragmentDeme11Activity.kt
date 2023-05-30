package com.example.jetpack_demos.fragments

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpack_demos.R
import com.google.android.material.appbar.MaterialToolbar

/**
 * App中的Toolbar(建立在Single Activity架构基础上)
 * 1.如果App的页面有一个统一的AppBar,可以将Toolbar放在Activity层作为ActionBar使用（必须做为ActionBar）
 * 2.如果App的页面具有特殊的自定义效果,需要控制比如AppBar的尺寸大小、位置、切换页面（fragment）时的动画、或者某个页面需要一个可折叠的Toolbar时，将Toolbar放在每一个Fragment页面上。
 */
class FragmentDeme11Activity : AppCompatActivity() {

    val viewModel by viewModels<ViewModel10Demo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment11_demo)
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        //没有这个Fragment中无法为Toolbar添加可选菜单项
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }


    //可以处理所有的可选菜单项的点击事件
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("Hello", "onOptionsItemSelected in Activity")
        val itemId = item.itemId
        when (itemId) {
            R.id.action_people -> {
                Log.d("Hello", "action_people onOptionsItemSelected in Activity")
                //即使这里返回false，其它fragment也不会收到该事件
                return true
            }

            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}