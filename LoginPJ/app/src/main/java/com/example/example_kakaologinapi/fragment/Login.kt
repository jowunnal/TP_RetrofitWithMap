package com.example.example_kakaologinapi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.example_kakaologinapi.viewModel.LoginViewModel
import com.example.example_kakaologinapi.R
import com.example.example_kakaologinapi.databinding.LoginBinding
import com.kakao.sdk.common.KakaoSdk

class Login : Fragment() {
    private var _binding : LoginBinding ?= null
    private val binding get() = _binding!!
    private val loginViewModel by lazy { LoginViewModel(requireActivity().application) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.login,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        KakaoSdk.init(requireActivity(), "255e031fc634ddb6120157a6e2e025e1")

        binding.lifecycleOwner=this
        binding.loginViewModel= loginViewModel
        loginViewModel.logFlag.observe(viewLifecycleOwner, Observer {
            if(it){
                Navigation.findNavController(view).navigate(R.id.action_log_to_home)
                loginViewModel.mutableLogFlag.value=false
            }
        })

    }
}