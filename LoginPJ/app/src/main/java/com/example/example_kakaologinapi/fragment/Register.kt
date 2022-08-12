package com.example.example_kakaologinapi.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.example_kakaologinapi.database.User
import com.example.example_kakaologinapi.databinding.RegisterBinding
import com.example.example_kakaologinapi.item.RegisterInfo
import com.example.example_kakaologinapi.viewModel.LoginViewModel
import com.example.example_kakaologinapi.viewModel.RegisterViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Register : Fragment() {
    private var _binding : RegisterBinding ?= null
    private val binding get() = _binding!!
    private val registerViewModel by lazy { ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(requireActivity().application))[RegisterViewModel::class.java] }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=RegisterBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerViewModel=registerViewModel
        binding.lifecycleOwner=viewLifecycleOwner
        binding.register=this

        binding.btnSignUP.setOnClickListener {
            if(binding.editTextName.text.isNullOrEmpty()|| binding.editTextID.text.isNullOrEmpty()||binding.editTextPW.text.isNullOrEmpty()||binding.editTextPWCheck.text.isNullOrEmpty()){
                Toast.makeText(requireActivity(),"작성하지 않은 항목이 있습니다.",Toast.LENGTH_SHORT).show()
            }
            else if(binding.etName.helperText?.isNotEmpty() == true || binding.etID.helperText?.isNotEmpty() == true ||
                binding.etPW.helperText?.isNotEmpty() == true || binding.etPWCheck.helperText?.isNotEmpty() == true) {
                Toast.makeText(requireActivity(),"올바른 형식으로 입력되지 않았습니다.",Toast.LENGTH_SHORT).show()
            }
            else{
                lifecycleScope.launch(Dispatchers.Main) {
                    val result= registerViewModel.registerUser()
                    if(result) {
                        Navigation.findNavController(view).popBackStack()
                    }
                }
            }
        }
    }


}