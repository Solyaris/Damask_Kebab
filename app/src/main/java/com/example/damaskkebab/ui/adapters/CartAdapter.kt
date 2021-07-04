package com.example.damaskkebab.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.damaskkebab.R
import com.example.damaskkebab.models.Food
import com.squareup.picasso.Picasso

class CartAdapter(private val context: Context) :
    ListAdapter<Food, CartViewHolder>(CartDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, context)
    }

}

class CartViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val foodTotalPrice: TextView = itemView.findViewById(R.id.cart_item_total_price)
    private val foodQuantity: TextView = itemView.findViewById(R.id.cart_item_quantity)
    private val foodImage: ImageView = itemView.findViewById(R.id.cart_item_image) as ImageView
    private val foodName: TextView = itemView.findViewById(R.id.cart_item_name)
    private val foodPrice: TextView = itemView.findViewById(R.id.cart_item_price)
    fun bind(item: Food, context: Context) {
        foodTotalPrice.text = (item.Price.toInt() * item.Quantity.toInt()).toString()
        foodQuantity.text = item.Quantity.toString()
        foodName.text = item.Name
        foodPrice.text = item.Price
        Picasso.with(context).load(item.Image)
            .into(foodImage)
    }

    companion object {
        fun from(parent: ViewGroup): CartViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_cart, parent, false)
            return CartViewHolder(view)
        }
    }
}


class CartDiffCallback : DiffUtil.ItemCallback<Food>() {
    override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem.foodId == newItem.foodId
    }

    override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem == newItem
    }

}