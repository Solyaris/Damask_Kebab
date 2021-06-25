package com.example.damaskkebab.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.damaskkebab.R
import com.example.damaskkebab.models.Food
import com.example.damaskkebab.ui.`interface`.ItemClickListener
import com.example.damaskkebab.ui.viewholder.FoodViewHolder
import com.example.damaskkebab.utils.ProductGridItemDecoration
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso

class FoodListFragment : Fragment() {


    var adapter: FirebaseRecyclerAdapter<Food, FoodViewHolder>? = null
    var recyclerView: RecyclerView? = null
    var foodList: DatabaseReference? = null
    var database: FirebaseDatabase? = null

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(R.layout.fragment_food_list, container, false)

        database =
            FirebaseDatabase.getInstance("https://damask-kebab-cc6f0-default-rtdb.europe-west1.firebasedatabase.app")

        foodList = database!!.getReference("Food")

        recyclerView = root.findViewById<View>(R.id.food_recycler_view) as RecyclerView
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        val largePadding = resources.getDimensionPixelSize(R.dimen.product_grid_spacing_large)
        val smallPadding = resources.getDimensionPixelSize(R.dimen.product_grid_spacing_small)
        recyclerView!!.addItemDecoration(ProductGridItemDecoration(largePadding, smallPadding))


        loadListFood(
            FoodListFragmentArgs.fromBundle(requireArguments()).categoryId
        )

        return root
    }

    private fun loadListFood(categoryId: String) {
        adapter = object : FirebaseRecyclerAdapter<Food, FoodViewHolder>(
            Food::class.java,
            R.layout.food_item,
            FoodViewHolder::class.java,
            foodList!!.orderByChild("MenuId").equalTo(categoryId)
        ) {
            override fun populateViewHolder(
                foodViewHolder: FoodViewHolder,
                model: Food,
                i: Int
            ) {
                foodViewHolder.foodName.text = model.Name
                foodViewHolder.foodPrice.text = model.Price
                Picasso.with(context).load(model.Image)
                    .into(foodViewHolder.foodImage)
                foodViewHolder.setItemClickListener(object : ItemClickListener {
                    override fun onClick(
                        view: View?,
                        position: Int,
                        isLongClick: Boolean
                    ) {
                        view!!.findNavController().navigate(
                            FoodListFragmentDirections.actionNavigationFoodListToFoodDetailFragment(
                                adapter!!.getRef(position).key!!
                            )
                        )
                    }
                })
            }
        }
        recyclerView!!.adapter = adapter
    }


}