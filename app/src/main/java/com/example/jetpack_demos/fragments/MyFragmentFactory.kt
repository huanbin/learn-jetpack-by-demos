package com.example.jetpack_demos.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

class MyFragmentFactory(val userReopository: UserReopository) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val clazz = Fragment4CustomFactoryDemo::class.java
        if (clazz == loadFragmentClass(classLoader, className)) {
            return Fragment4CustomFactoryDemo(userReopository)
        }
        return super.instantiate(classLoader, className)
    }
}