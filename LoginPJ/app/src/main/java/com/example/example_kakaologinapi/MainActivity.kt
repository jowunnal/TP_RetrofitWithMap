package com.example.example_kakaologinapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.example_kakaologinapi.database.User
import com.example.example_kakaologinapi.databinding.ActivityMainBinding
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.user.UserApiClient

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val vm by lazy{LoginViewModel(application)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.loginViewModel= ViewModelProvider(this)[LoginViewModel::class.java]

        vm.getUser().observe(this,Observer{
            vm.userLiveData.value=it
            binding.textView.text=it.toString()
        })


        KakaoSdk.init(this, "255e031fc634ddb6120157a6e2e025e1")

        UserApiClient.instance.loginWithKakaoTalk(this){token, error ->  
            if(error != null){
                Log.e("Tag","Login 실패")
            }
            else if (token!=null){
                Log.e("Tag","로그인 성공")
            }
        }
    }
}