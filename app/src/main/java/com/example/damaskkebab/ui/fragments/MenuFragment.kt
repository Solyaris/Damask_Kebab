package com.example.damaskkebab.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.damaskkebab.R
import com.example.damaskkebab.models.Category
import com.example.damaskkebab.ui.`interface`.ItemClickListener
import com.example.damaskkebab.ui.viewholder.CategoryViewHolder
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso


class MenuFragment : Fragment() {
    var adapter: FirebaseRecyclerAdapter<Category, CategoryViewHolder>? = null
    var database: FirebaseDatabase? = null
    var categories: DatabaseReference? = null
    var recyclerMenu: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(R.layout.fragment_menu, container, false)

        database =
            FirebaseDatabase.getInstance("https://damask-kebab-cc6f0-default-rtdb.europe-west1.firebasedatabase.app")
        categories = database!!.getReference("Category")

        Log.d("Category ", categories.toString())
        //Load menu
        recyclerMenu = root.findViewById<View>(R.id.category_recycler_view) as RecyclerView
        recyclerMenu!!.setHasFixedSize(true)
        recyclerMenu!!.layoutManager = LinearLayoutManager(context)

        loadMenu()
        return root
    }

    private fun loadMenu() {
        adapter = object : FirebaseRecyclerAdapter<Category, CategoryViewHolder>(
            Category::class.java,
            R.layout.category_item,
            CategoryViewHolder::class.java,
            categories
        ) {
            override fun populateViewHolder(
                viewHolder: CategoryViewHolder,
                model: Category,
                i: Int
            ) {
                viewHolder.menuName.text = model.Name
                Picasso.with(activity!!.baseContext).load(model.Image)
                    .into(viewHolder.imageView)
                val clickItem = model
                viewHolder.setItemClickListener(object : ItemClickListener {
                    override fun onClick(
                        view: View?,
                        position: Int,
                        isLongClick: Boolean
                    ) {
                        view!!.findNavController().navigate(MenuFragmentDirections.actionNavigationMenuToFoodListFragment(
                            adapter!!.getRef(position).key!!)
                            )

                    }
                })
            }
        }
        recyclerMenu!!.adapter = adapter
    }
}