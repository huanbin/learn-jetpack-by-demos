package com.example.jetpack_demos.fragments

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.jetpack_demos.R

class FragmentDemo2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment2_demo)
        if (savedInstanceState == null) {
            val args = bundleOf("name" to "json")
            //动态添加fragment
            supportFragmentManager.commit {
                //实例化Fragment1Demo，添加实例到容器中
                add<Fragment1Demo>(R.id.fragmentContainerView, "tag1", args = args)
                //优化事务中的fragment的状态变化，以便动画和过渡正常工作----》
                // 意思就是优化事务中的冗余操作，比如一个或多个事务中有添加Fragment A,之后又删除Fragment A,优化之后，Fragment A就不会经过create到destory的声明周期了，被直接忽略。
                setReorderingAllowed(true)
                //添加到回退栈
                addToBackStack("A")

                //设置主导航fragment,处理导航action
//                setPrimaryNavigationFragment()
            }

            supportFragmentManager.commit {
                replace<Fragment2Demo>(R.id.fragmentContainerView, "tag2")
                replace<Fragment3Demo>(R.id.fragmentContainerView, "tag3")
                setReorderingAllowed(true)
                addToBackStack("B")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        //获取fragment
        //第一种方法
//        val fragment =
//            supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
        //第二种方法
        val fragment = supportFragmentManager.findFragmentByTag("tag2")

        Toast.makeText(this, "${fragment?.javaClass?.simpleName}", Toast.LENGTH_SHORT).show()
    }
}