package com.example.my_salon.view.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.my_salon.R
import com.example.my_salon.databinding.FragmentLoginBinding
import com.example.my_salon.databinding.FragmentOrdersBinding
import com.google.android.gms.dynamic.SupportFragmentWrapper

class OrderFragment: Fragment() {

    private var _binding: FragmentOrdersBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.title = getString(R.string.my_orders)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrdersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            binding.archiveOrders.setOnClickListener { //todo условие если лист пуст, то показывать фрагмент НЕТ записей
                viewFragment(EmptyOrderFragment())
            }

        binding.archiveOrders.setOnClickListener { //todo условие если лист пуст, то показывать фрагмент НЕТ записей
            viewFragment(EmptyOrderFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun viewFragment(fragment: Fragment) {

        val fragmentManager = childFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.orders_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}