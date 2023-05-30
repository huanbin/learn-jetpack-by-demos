package com.example.jetpack_demos.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

abstract class BaseFragment<B : ViewDataBinding> : Fragment(), View.OnClickListener {

    lateinit var binding: B
    lateinit var navController: NavController

    abstract fun layout(): Int

    //不让子类重写
    final override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("EV onCreateView in ${javaClass.simpleName}")
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            layoutInflater,
            layout(),
            container,
            false
        )
        navController = findNavController()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("EV onViewCreated in ${javaClass.simpleName}")
    }

    override fun onDetach() {
        super.onDetach()
        println("EV onDetach in ${javaClass.simpleName}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("EV onDestroyView in ${javaClass.simpleName}")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("EV onDestroy in ${javaClass.simpleName}")
    }
}