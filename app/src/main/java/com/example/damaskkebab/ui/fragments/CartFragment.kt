  package com.example.damaskkebab.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.damaskkebab.R
import com.example.damaskkebab.database.FoodDatabase
import com.example.damaskkebab.models.Food
import com.example.damaskkebab.ui.viewholder.CartViewHolder
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*

class CartFragment : Fragment() {

    // private lateinit var dashboardViewModel: DashboardViewModel
    var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //  dashboardViewModel =
//        ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_cart, container, false)

        val application = requireNotNull(this.activity).application
        val foodDao = FoodDatabase.getInstance(application).foodDatabaseDao
        val scope = CoroutineScope(Job() + Dispatchers.IO)


        val recyclerView = root.findViewById<View>(R.id.cart_recycler_view) as RecyclerView
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        var cartList: MutableList<Food> = mutableListOf()
        val adapter = CartAdapter(cartList, this.requireContext())
        recyclerView.adapter = adapter

        scope.launch {
            cartList = foodDao.getAllFood() as MutableList<Food>
            adapter.notifyDataSetChanged()
        }

        return root
    }

    class CartAdapter(private val foodList: List<Food>, private val context: Context) :
        RecyclerView.Adapter<CartViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.cart_item, parent, false)
            return CartViewHolder(view)
        }

        override fun onBindViewHolder(holder: CartViewHolder, position: Int) {

            holder.cartName.text = foodList[position].Name
            holder.cartPrice.text = foodList[position].Price
            Picasso.with(context).load(foodList[position].Image)
                .into(holder.cartImage)

        }

        override fun getItemCount() = foodList.size


    }
}

