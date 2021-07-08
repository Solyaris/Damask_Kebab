package com.example.damaskkebab.ui.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.damaskkebab.R
import com.example.damaskkebab.ui.`interface`.ItemClickListener

class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener {

    var foodImage: ImageView = itemView.findViewById(R.id.food_image)
    var foodName: TextView = itemView.findViewById(R.id.food_item_title)
    var foodPrice: TextView = itemView.findViewById(R.id.food_item_add_tv)

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