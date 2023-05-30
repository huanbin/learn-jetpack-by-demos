package com.example.jetpack_demos.navigation.dsl

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.jetpack_demos.R


class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        val textView = view.findViewById<TextView>(R.id.tvData)

        textView.text = arguments?.getString("id")

        val search:SearchParameters?= arguments?.getParcelable<SearchParameters>("search")
        Log.d("Hello",search.toString())


        textView.setOnClickListener {
//            findNavController().navigate("login")
            findNavController().navigate("loginRoute")
        }
        return view
    }
}