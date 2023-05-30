package com.example.jetpack_demos.navigation.dsl

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack_demos.R
import com.example.jetpack_demos.navigation.dsl.placeholder.PlaceholderContent
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * A fragment representing a list of Items.
 */
class ItemFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MyItemRecyclerViewAdapter(PlaceholderContent.ITEMS) { item, _ ->
//                    第一种传对象的json字符串
                   /* val searchParameters = SearchParameters("json", listOf("a", "ab"))
                    val jsonString = Uri.encode(Json.encodeToString(searchParameters))
                    Log.d("Hello", "传参json=$jsonString")
                    findNavController().navigate("detail/${item.id}?search=$jsonString")*/
//                    第二种传对象SearchParameters
                    /*val searchParameters = SearchParameters("json", listOf("a", "ab"))
                    findNavController().navigate("detail/${item.id}?search=$searchParameters")*/
//                  第三种deeplink传参
                    val searchParameters = SearchParameters("json", listOf("a", "ccc"))
                    val jsonString = Uri.encode(Json.encodeToString(searchParameters))
                    //这里直接传对象没有获取到，可能与格式有关
                    val  uri="http://www.example.com/plants/?id=${item.id}&search=$jsonString"
                    val action="android.intent.action.MY_ACTION"
                    val mimeType="image/png"
                    val navDeepLinkRequest =
                        NavDeepLinkRequest.Builder.fromUri(Uri.parse(uri)).setAction(action).setMimeType(mimeType)
                            .build()
                    findNavController().navigate(navDeepLinkRequest)

                }
            }
        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}