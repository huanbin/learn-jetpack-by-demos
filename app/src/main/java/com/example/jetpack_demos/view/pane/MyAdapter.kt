package com.example.jetpack_demos.view.pane

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack_demos.R

class MyAdapter(val datas: List<String>, val listener: (index: Int, item: String) -> Unit) :
    RecyclerView.Adapter<MyAdapter.MyHold>() {

    class MyHold(view: View) : RecyclerView.ViewHolder(view) {

        var tvTitle: TextView

        init {
            tvTitle = view.findViewById(R.id.tvTitle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHold {
        return MyHold(
            LayoutInflater.from(parent.context).inflate(
                R.layout.sliding_rv_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: MyHold, position: Int) {
        holder.tvTitle.text = datas[position]
        holder.tvTitle.setOnClickListener {
            listener(position, datas[position])
        }
    }
}