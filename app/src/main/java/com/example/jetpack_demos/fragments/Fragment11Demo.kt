package com.example.jetpack_demos.fragments

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.jetpack_demos.R

class Fragment11Demo : Fragment(R.layout.fragment_demo11) {

    private var isEditing = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //通知系统，Fragment参与AppBar的可选菜单的填充
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btSave).setOnClickListener {
            isEditing=true
            //声明菜单已发生改变，需要重新创建
            requireActivity().invalidateOptionsMenu()
        }
    }

    /**
     * 每次显示Menu前调用
     */
    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val menuItem = menu.findItem(R.id.action_done)
        menuItem.isVisible = isEditing
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_menu_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("Hello", "onOptionsItemSelected in Fragment")
        val itemId = item.itemId
        when (itemId) {
            R.id.action_settings -> {
                Log.d("Hello", "action settings onOptionsItemSelected in Fragment")
                return true
            }

            R.id.action_done -> {
                Log.d("Hello", "action done onOptionsItemSelected in Fragment")
                isEditing = false
                //声明菜单已发生改变，需要重新创建
                requireActivity().invalidateOptionsMenu()
                return true
            }

            else -> {
                return super.onOptionsItemSelected(item)
            }
        }

    }
}