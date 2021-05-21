package com.example.damaskkebab.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.damaskkebab.R

class ProfileFragment : Fragment() {

//  private lateinit var notificationsViewModel: NotificationsViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
//    notificationsViewModel =
//            ViewModelProvider(this).get(NotificationsViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_profile, container, false)
    val textView: TextView = root.findViewById(R.id.text_notifications)
      textView.text = ""
    return root
  }
}