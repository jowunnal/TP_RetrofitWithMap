package com.example.example_kakaologinapi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.example_kakaologinapi.viewModel.LoginViewModel
import com.example.example_kakaologinapi.R
import com.example.example_kakaologinapi.databinding.LoginBinding
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Login : Fragment() {
    private var _binding : LoginBinding ?= null
    private val binding get() = _binding!!
    private val loginViewModel : LoginViewModel by viewModels()
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
        KakaoSdk.init(requireActivity(), requireActivity().getString(R.string.nativeKey))
        binding.lifecycleOwner=viewLifecycleOwner
        binding.loginViewModel= loginViewModel
        binding.navigation=Navigation.findNavController(view)

        loginViewModel.logFlag.observe(viewLifecycleOwner, Observer {
            if(it==1){
                Navigation.findNavController(view).navigate(R.id.action_log_to_home)
                loginViewModel.mutableLogFlag.value=0
            }
        })

    }
}