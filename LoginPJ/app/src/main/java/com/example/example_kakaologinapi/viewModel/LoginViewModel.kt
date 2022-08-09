package com.example.example_kakaologinapi.viewModel
import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.example_kakaologinapi.database.LoginDatabase
import com.example.example_kakaologinapi.repository.LoginRepository
import com.example.example_kakaologinapi.database.User
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.*

class LoginViewModel(private val app: Application) : AndroidViewModel(app) {
    private val repository= LoginRepository.getInstance(app.applicationContext)
    var mutableLogFlag = MutableLiveData(false)
    val logFlag: LiveData<Boolean> get() = mutableLogFlag

    fun loginUser(userId:String,userPw:String) {
        viewModelScope.launch(Dispatchers.Main) {
            val user= withContext(Dispatchers.IO) { repository.getUser() }
            for(data in user){
                if(userId==data.userId&&userPw==data.userPw){
                    mutableLogFlag.value=true
                    continue
                }
                else if(userId==data.userId&&userPw!=data.userPw){
                    Toast.makeText(app,"비밀번호가 일치하지 않습니다.",Toast.LENGTH_SHORT).show()
                    continue
                }
            }
            Toast.makeText(app,"존재하지 않는 아이디 입니다.",Toast.LENGTH_SHORT).show()
        }
    }

    fun loginKakao(){
        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.accessTokenInfo { _, error ->
                if (error != null) {
                    if (error is KakaoSdkError && error.isInvalidTokenError()) {
                        loginWithKakaoAccount()
                    }
                    else {
                        Log.d("test","카카오 로그인 에러")
                    }
                }
                else {
                    //토큰 유효성 체크 성공(필요 시 토큰 갱신됨)
                }
            }
        }
        else {
            loginWithKakaoAccount()
        }
    }
    fun loginWithKakaoAccount() {
        UserApiClient.instance.loginWithKakaoAccount(app.applicationContext) { token, error ->
            if (error != null) {
                Log.e("Tag", "Login 실패")
            } else if (token != null) {
                Log.e("Tag", "로그인 성공")
            }
        }
    }

    fun addUser(id:String,pw:String){
        viewModelScope.launch(Dispatchers.IO) { repository.addUser(User(id,"jinho",pw)) }
        Log.d("test", "$id $pw")
    }

    fun deleteUser(id:String,pw:String){
        viewModelScope.launch(Dispatchers.IO) { repository.deleteUser(User(id,"jinho",pw)) }
    }
}