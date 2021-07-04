package com.example.damaskkebab.ui.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.damaskkebab.R
import com.example.damaskkebab.database.FoodDatabase
import com.example.damaskkebab.databinding.FragmentCartBinding
import com.example.damaskkebab.ui.adapters.CartAdapter
import com.example.damaskkebab.ui.viewmodels.CartViewModel
import com.example.damaskkebab.ui.viewmodels.CartViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class CartFragment : Fragment() {

    private lateinit var cartViewModel: CartViewModel
    var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentCartBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_cart, container, false
        )
        binding.lifecycleOwner = this
        val application = requireNotNull(this.activity).application

        val foodDao = FoodDatabase.getInstance(application).foodDatabaseDao
        val scope = CoroutineScope(Job() + Dispatchers.Main)

        val viewModelFactory = CartViewModelFactory(foodDao, application)
        cartViewModel = ViewModelProvider(this, viewModelFactory).get(CartViewModel::class.java)
        binding.cartViewModel = cartViewModel

        binding.cartRecyclerView
        binding.cartRecyclerView.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        val adapter = CartAdapter(this.requireContext())
        binding.cartRecyclerView.adapter = adapter
        cartViewModel.foods.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
                Log.d(TAG, "onCreateView: $it")
            }
        })

        return binding.root
    }


}

