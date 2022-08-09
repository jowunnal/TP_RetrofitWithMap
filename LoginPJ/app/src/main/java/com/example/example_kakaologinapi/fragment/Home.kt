package com.example.example_kakaologinapi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.example_kakaologinapi.databinding.HomeBinding
import com.example.example_kakaologinapi.viewModel.ApiViewModel

class Home : Fragment() {
    private var _binding : HomeBinding ?= null
    private val binding get() = _binding !!
    private val apiViewModel by lazy { ApiViewModel(requireActivity().application) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apiViewModel=apiViewModel
        binding.lifecycleOwner=this
        apiViewModel.restaurantList.observe(viewLifecycleOwner, Observer {
            binding.tvApi.text=it.toString()
        })
    }
}