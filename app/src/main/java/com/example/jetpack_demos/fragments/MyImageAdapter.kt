package com.example.jetpack_demos.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jetpack_demos.R

class MyImageAdapter(
    private val onItemClick: (item: MyImgItem, view: View) -> Unit
) :
    ListAdapter<MyImgItem, MyImageAdapter.Companion.MyImageViewHolder>(differCallback) {

    companion object {

        val differCallback = object : ItemCallback<MyImgItem>() {
            override fun areItemsTheSame(oldItem: MyImgItem, newItem: MyImgItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MyImgItem, newItem: MyImgItem): Boolean {
                return oldItem == newItem
            }

        }

        class MyImageViewHolder(
            view: View,
            val onItemClick: (item: MyImgItem, view: View) -> Unit
        ) :
            RecyclerView.ViewHolder(view) {

            private val imgView: ImageView

            init {
                imgView = view.findViewById(R.id.imgView)
            }

            fun bindData(item: MyImgItem) {
                ViewCompat.setTransitionName(imgView, "img-${item.id}")
                imgView.setOnClickListener {
                    onItemClick(item, imgView)
                }
                Glide.with(imgView)
                    .load(item.path)
                    .into(imgView)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyImageViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_img_item, parent, false)
        return MyImageViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: MyImageViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}