package com.example.my_salon.view.orders

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.my_salon.MainActivity
import com.example.my_salon.databinding.ContentNoOrderBinding

class EmptyOrderFragment: Fragment() {

    private var _binding: ContentNoOrderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ContentNoOrderBinding.inflate(layoutInflater, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.redirectToHome.setOnClickListener {
            startActivity(Intent(context, MainActivity::class.java))
        }
    }
}