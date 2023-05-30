package com.example.jetpack_demos.navigation.multistack.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.example.jetpack_demos.R
import com.example.jetpack_demos.databinding.FragmentHomeBinding

/***
 * 导航库中有显式和隐私deeplink
 * 隐式deeplink与启动activity（没有导航库）的隐式intent类似，一般用于第三方打开App，任务栈不会被清除
 * 显式deeplink与启动activity（没有导航库）指定activity component组件名类似,一般用于通知和app小部件，任务栈会被清除，然后重建
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("Hello", "${javaClass.simpleName} onCreateView")
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        Log.d("Hello", "HomeFragment user :${arguments?.getString("user")}")
        Log.d("Hello", "HomeFragment sex :${arguments?.getString("sex")}")


        val textView: TextView = binding.textHome
        textView.setOnClickListener {
//            deepLink1()
//            deepLink2()
            findNavController().navigate(R.id.homeToSecond)
        }

        val button7 = binding.button7
        button7.setOnClickListener {
            val url = "http://www.shangyexinzhi.com"
//            val url = "http://www.hb.com"
            val uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    /**
     * 显式deeplink
     */
    fun deepLink1() {
        val pendingIntent = NavDeepLinkBuilder(requireContext())
            .setGraph(R.navigation.mobile_navigation)
            //性别信息（传递给目的地的参数，注意需要在xml中声明参数）
            .setDestination(R.id.homeSecondFragment, bundleOf("sex" to 1))
            //用户信息（传递给任务栈上每一个目的地的参数）
            .setArguments(bundleOf("user" to "json"))
            .createPendingIntent()
        pendingIntent.send()
    }

    /**
     * 隐式deeplink
     * 路劲参数参与deeplink匹配，优先顺序：uri action mimetype verify
     * 查询参数不参与匹配,但是查询参数必须在uri中声明
     */
    fun deepLink2() {
        val navDeepLinkRequest =
            NavDeepLinkRequest.Builder.fromUri("http://hb.com/user/1?sex=1&user=json".toUri())
                .build()
        findNavController().navigate(navDeepLinkRequest)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d("Hello", "${javaClass.simpleName} onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Hello", "${javaClass.simpleName} onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("Hello", "${javaClass.simpleName} onDetach")
    }
}