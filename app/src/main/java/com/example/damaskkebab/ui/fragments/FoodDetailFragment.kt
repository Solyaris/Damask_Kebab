package com.example.damaskkebab.ui.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.damaskkebab.R
import com.example.damaskkebab.database.FoodDatabase
import com.example.damaskkebab.models.Food
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_food_detail.*
import kotlinx.android.synthetic.main.fragment_food_detail.food_name
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FoodDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FoodDetailFragment : Fragment() {

    var foodList: DatabaseReference? = null
    var database: FirebaseDatabase? = null
    var currentFood: Food? = null
    var collapsingToolbarLayout: CollapsingToolbarLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(R.layout.fragment_food_detail, container, false)

        database =
            FirebaseDatabase.getInstance("https://damask-kebab-cc6f0-default-rtdb.europe-west1.firebasedatabase.app")
        foodList = database!!.getReference("Food")

        getDetailFood(FoodDetailFragmentArgs.fromBundle(requireArguments()).foodId)

        val application = requireNotNull(this.activity).application
        val foodDao = FoodDatabase.getInstance(application).foodDatabaseDao
                val scope = CoroutineScope(Dispatchers.Default)

        val buttonCart = root.findViewById<View>(R.id.btnCart) as FloatingActionButton
        buttonCart.setOnClickListener {
            scope.launch {
                currentFood?.let { it1 -> foodDao.insert(it1) }
                Log.d(TAG, "onCreateView: ${foodDao.getAllFood()}")
            }
        }

        return root
    }

    private fun getDetailFood(foodId: String) {
        Log.d(TAG, "getDetailFood: $foodId")
        foodList!!.child(foodId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                currentFood = snapshot.getValue(Food::class.java)
                currentFood!!.foodId = foodId.toLong()
                Picasso.with(context).load(currentFood?.Image)
                    .into(img_food)
                collapsingToolbarLayout?.title = currentFood?.Name
                food_price.text = currentFood?.Price
                food_name.text = currentFood?.Name
                food_description.text = currentFood?.Description
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

}