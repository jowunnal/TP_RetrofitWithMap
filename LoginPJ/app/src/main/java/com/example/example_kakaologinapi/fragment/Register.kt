package com.example.example_kakaologinapi.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.example_kakaologinapi.database.User
import com.example.example_kakaologinapi.databinding.RegisterBinding
import com.example.example_kakaologinapi.viewModel.LoginViewModel

class Register : Fragment() {
    private var _binding : RegisterBinding ?= null
    private val binding get() = _binding!!
    private val loginViewModel by lazy { ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(requireActivity().application))[LoginViewModel::class.java] }
    private val user by lazy { User("","","") }
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
        binding.loginViewModel=loginViewModel
        binding.lifecycleOwner=this
        binding.user=user
        binding.navigation=Navigation.findNavController(view)

        binding.btnSignUP.setOnClickListener {
            loginViewModel.registerUser(user)
            Navigation.findNavController(view).popBackStack()
            Log.d("test",user.toString())
        }

    }
    fun checkNameOrId(inputString:String,nameOrId: Boolean){
        when(nameOrId){
            true->{
                if(inputString.contains("/[^0-9a-zA-Zㄱ-힣]/")){
                    binding.etName.helperText="특수문자가 입력될수 없습니다."
                }
                else
                    binding.etName.helperText=""
            }
            false->{
                if(inputString.contains("/[^0-9a-zA-Zㄱ-힣]/")){
                    binding.etID.helperText="특수문자가 입력될수 없습니다."
                }
                else
                    binding.etID.helperText=""
            }
        }

    }

    fun checkPw(inputString: String){
        if(inputString.contains("/[^0-9a-zA-Zㄱ-힣!@#*]/")){
            binding.etPW.helperText="영대소문자,숫자,특수문자(!,@,#,*)만 가능합니다."
        }
        else
            binding.etPW.helperText=""
    }

    fun checkPwDouble(inputString: String){
        if(inputString!=user.userPw){
            binding.etPW.helperText="비밀번호가 일치하지 않습니다."
        }
        else
            binding.etPW.helperText=""
    }
}