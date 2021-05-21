package com.example.damaskkebab.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.damaskkebab.R

class MenuFragment : Fragment() {

//  private lateinit var homeViewModel: HomeViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
//    homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_menu, container, false)
    val textView: TextView = root.findViewById(R.id.text_home)
      textView.text = ""
    return root
  }
}