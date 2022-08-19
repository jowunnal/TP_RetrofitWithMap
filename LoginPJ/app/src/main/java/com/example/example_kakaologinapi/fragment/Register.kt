package com.example.example_kakaologinapi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.example_kakaologinapi.databinding.RegisterBinding
import com.example.example_kakaologinapi.viewModel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Register : Fragment() {
    private var _binding : RegisterBinding ?= null
    private val binding get() = _binding!!
    private val registerViewModel :RegisterViewModel by viewModels()
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

        binding.btnSignUP.setOnClickListener {
            if(binding.editTextName.text.isNullOrEmpty()|| binding.editTextID.text.isNullOrEmpty()||binding.editTextPW.text.isNullOrEmpty()||binding.editTextPWCheck.text.isNullOrEmpty()){
                Toast.makeText(requireActivity(),"작성하지 않은 항목이 있습니다.",Toast.LENGTH_SHORT).show()
            }
            else if(binding.etName.helperText?.isNotEmpty() == true || binding.etID.helperText?.isNotEmpty() == true ||
                binding.etPW.helperText?.isNotEmpty() == true || binding.etPWCheck.helperText?.isNotEmpty() == true) {
                Toast.makeText(requireActivity(),"올바른 형식으로 입력되지 않았습니다.",Toast.LENGTH_SHORT).show()
            }
            else if(binding.editTextPW.text.toString()!=binding.editTextPWCheck.text.toString()){
                Toast.makeText(requireActivity(),"비밀번호가 일치하지 않습니다.",Toast.LENGTH_SHORT).show()
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