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
import androidx.navigation.Navigation
import com.example.example_kakaologinapi.database.User
import com.example.example_kakaologinapi.databinding.RegisterBinding
import com.example.example_kakaologinapi.viewModel.LoginViewModel
import com.google.android.material.textfield.TextInputEditText

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
        binding.register=this
        binding.btnSignUP.setOnClickListener {
            if(binding.etName.helperText.isNullOrEmpty()|| binding.etID.helperText.isNullOrEmpty()||binding.etPW.helperText.isNullOrEmpty()||binding.etPWCheck.helperText.isNullOrEmpty()){
                loginViewModel.registerUser(user)
                Navigation.findNavController(view).popBackStack()
                Log.d("test",user.toString())
            }
            else{
                Toast.makeText(requireActivity(),"올바른 형식으로 입력되지 않았습니다.",Toast.LENGTH_SHORT).show()
            }
        }

    }

    val checkNameIsCorrect = fun(inputString:String){
        if(inputString.contains(Regex("[^0-9a-zA-Zㄱ-힣_]"))){
            binding.etName.helperText="특수문자(_)를 제외하고 입력될수 없습니다."
        }
        else
            binding.etName.helperText=""
    }

    val checkIdIsCorrect = fun(inputString:String){
        if(inputString.contains("[^0-9a-zA-Zㄱ-힣]".toRegex())){
            binding.etID.helperText="특수문자는 입력될수 없습니다."
        }
        else
            binding.etID.helperText=""
    }

    val checkPwIsCorrect = fun(inputString: String){
        if(inputString.contains("[^0-9a-zA-Zㄱ-힣!@#*]".toRegex())){
            binding.etPW.helperText="영대소문자,숫자,특수문자(!,@,#,*)만 가능합니다."
        }
        else
            binding.etPW.helperText=""
    }

    val checkPwDoubleIsCorrect = fun(inputString: String){
        if(inputString!=user.userPw){
            binding.etPWCheck.helperText="비밀번호가 일치하지 않습니다."
        }
        else
            binding.etPWCheck.helperText=""
    }
}