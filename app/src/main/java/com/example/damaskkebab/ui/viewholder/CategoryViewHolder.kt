package com.example.damaskkebab.ui.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.damaskkebab.R
import com.example.damaskkebab.ui.`interface`.ItemClickListener


class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener {
    var menuName: TextView = itemView.findViewById(R.id.category_title)
    var imageView: ImageView = itemView.findViewById(R.id.category_image) as ImageView
    private var itemClickListener: ItemClickListener? = null
    fun setItemClickListener(itemClickListener: ItemClickListener?) {
        this.itemClickListener = itemClickListener
    }

    override fun onClick(v: View?) {
        itemClickListener!!.onClick(v, adapterPosition, false)
    }

    init {
        itemView.setOnClickListener(this)
    }
}