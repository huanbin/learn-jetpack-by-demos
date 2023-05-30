package com.example.jetpack_demos.draganddrop

import android.content.ClipData
import android.content.ClipDescription
import android.content.ClipDescription.MIMETYPE_TEXT_PLAIN
import android.content.ClipDescription.MIMETYPE_TEXT_URILIST
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.View.DRAG_FLAG_GLOBAL
import android.view.View.DRAG_FLAG_GLOBAL_URI_READ
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.DragStartHelper
import androidx.core.view.ViewCompat
import androidx.draganddrop.DropHelper
import com.bumptech.glide.Glide
import com.example.jetpack_demos.databinding.ActivityDragAndDropBinding

class DragAndDropActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDragAndDropBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val image = "https://cdn.pixabay.com/photo/2021/05/06/16/13/children-6233868_960_720.png"
        val image2 = "https://cdn.pixabay.com/photo/2023/01/22/16/40/monkey-7736916_960_720.png"
        Glide.with(this).load(image).into(binding.imageView)
//        Glide.with(this).load(image).into(binding.imageView2)


        DragStartHelper(
            binding.text
        ) { v, helper ->
            val itemText = ClipData.Item("world")
            val clipData = ClipData(
                ClipDescription(
                    "text",
                    arrayOf(MIMETYPE_TEXT_PLAIN)
                ), itemText
            )
            val dragShadowBuilder = View.DragShadowBuilder(v)
            v.startDragAndDrop(clipData, dragShadowBuilder, null, DRAG_FLAG_GLOBAL)
        }.attach()

        DragStartHelper(
            binding.imageView
        ) { v, helper ->
//            val clipData = ClipData.newRawUri("image",Uri.parse(image2))
            val itemImage = ClipData.Item(Uri.parse(image2))
            val clipData = ClipData(
                ClipDescription(
                    "image",
                    arrayOf(MIMETYPE_TEXT_URILIST)
                ), itemImage
            )
            val dragShadowBuilder = View.DragShadowBuilder(v)
            v.startDragAndDrop(
                clipData,
                dragShadowBuilder,
                null,
                //注意这里需要DRAG_FLAG_GLOBAL_URI_READ，获取读取uri的权限
                DRAG_FLAG_GLOBAL.or(DRAG_FLAG_GLOBAL_URI_READ)
            )
        }.attach()



        DropHelper.configureView(
            this, binding.imageView2,
            arrayOf(MIMETYPE_TEXT_URILIST),
            DropHelper.Options.Builder()
                //.addInnerEditTexts(binding.et)
                .build()
        ) { view, payload ->
            val partition = payload.partition { item ->
                item.uri != null
            }
            val first = partition.first
            val second = partition.second
            println("first=$first")
            println("second=$second")
            val uri = first.clip.getItemAt(0).uri
            Glide.with(this@DragAndDropActivity).load(uri).into(binding.imageView2)
            null
        }

        ViewCompat.setOnReceiveContentListener(
            binding.et,
            arrayOf(MIMETYPE_TEXT_URILIST)
        ) { view, payload ->
            val text = payload.clip.getItemAt(0).text
            binding.et.setText(text)
            null
        }
    }

}