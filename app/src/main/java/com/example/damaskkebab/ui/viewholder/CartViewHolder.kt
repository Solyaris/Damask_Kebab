package com.example.damaskkebab.ui.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.damaskkebab.R
import kotlinx.android.synthetic.main.cart_item.view.*

class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    var cartImage: ImageView = itemView.findViewById(R.id.cart_item_image) as ImageView
    var cartName: TextView = itemView.findViewById(R.id.cart_item_name)
    var cartPrice: TextView = itemView.findViewById(R.id.cart_item_price)
}
