package com.example.example_kakaologinapi.viewModel

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.example.example_kakaologinapi.database.User
import com.example.example_kakaologinapi.item.RegisterInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(app: Application) :ManageMemberViewModel(app) {
    val info by lazy{ RegisterInfo(ObservableField(""),ObservableField(""),ObservableField(""),ObservableField("")) }

    suspend fun registerUser():Boolean{
        var result=false
        viewModelScope.launch(Dispatchers.Main){
            val job = launch(Dispatchers.Main){//main스레드인이유는 checkUser()에서 메인스레드영역의 리소스를 사용하기때문. toast와 livedata.setvalue()
                checkUser(user,"이미 존재하는 아이디 입니다.","이미 존재하는 아이디 입니다.",user.userName+"님의 회원가입이 완료되었습니다.") }
            job.join()
            if(logFlag.value==0) {
                repository.addUser(user)
                result=true
                Log.d("test", "pres: ${user.userId} ${user.userPw}")
            }
            mutableLogFlag.value=0
        }.join()
        return result
    }

    fun deleteUser(id:String,pw:String){
        viewModelScope.launch(Dispatchers.IO) { repository.deleteUser(User(id,"jinho",pw)) }
    }

    private fun checkRegisterInfoIsCorrect(inputString: String, pattern:String, text:String):String{
        var txt=""
        if(inputString.contains(pattern.toRegex())){
            txt = text
        }
        return txt
    }

    val checkNameIsCorrect = fun(inputString:String){
        info.r_Name.set(checkRegisterInfoIsCorrect(inputString,"[^0-9a-zA-Zㄱ-힣_]","특수문자는 (_)를 제외하고 입력될 수 없습니다."))
    }

    val checkIdIsCorrect = fun(inputString:String){
        info.r_Id.set(checkRegisterInfoIsCorrect(inputString,"[^0-9a-zA-Zㄱ-힣]","특수문자는 입력될수 없습니다."))
    }

    val checkPwIsCorrect = fun(inputString: String){
        info.r_Pw.set(checkRegisterInfoIsCorrect(inputString,"[^0-9a-zA-Zㄱ-힣!@#*]","영대소문자,숫자,특수문자(!,@,#,*)만 가능합니다."))
    }

    val checkPwDoubleIsCorrect = fun(inputString: String){
        if(inputString!=user.userPw){
            info.r_PwDouble.set("비밀번호가 일치하지 않습니다.")
        }
        else
            info.r_PwDouble.set("")
    }
}