package com.example.example_kakaologinapi.viewModel
import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.example.example_kakaologinapi.repository.LoginRepository
import com.example.example_kakaologinapi.database.User
import com.example.example_kakaologinapi.databinding.RegisterBinding
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.*
import kotlin.math.log

class LoginViewModel(private val app: Application) : AndroidViewModel(app) {
    private val repository= LoginRepository.getInstance(app.applicationContext)
    var mutableLogFlag = MutableLiveData(0)
    val logFlag: LiveData<Int> get() = mutableLogFlag

    fun loginUser(client:User){
        viewModelScope.launch(Dispatchers.Main){checkUser(client,"환영합니다.","비밀번호가 틀렸습니다.","존재하지 않는 계정 입니다.")}
    }
    suspend fun registerUser(user:User):Boolean{
        var result=false
        viewModelScope.launch(Dispatchers.Main){
            val job = launch{checkUser(user,"이미 존재하는 아이디 입니다.","이미 존재하는 아이디 입니다.",user.userName+"님의 회원가입이 완료되었습니다.")}
            job.join()
            if(logFlag.value==0) {
                repository.addUser(user)
                result=true
                Log.d("test", "${user.userId} ${user.userPw}")
            }
            mutableLogFlag.value=0
        }.join()
        return result
    }

    fun deleteUser(id:String,pw:String){
        viewModelScope.launch(Dispatchers.IO) { repository.deleteUser(User(id,"jinho",pw)) }
    }

    suspend fun checkUser(client:User,succeedText:String,wrongText:String,notFoundText:String) {
        val user= withContext(Dispatchers.IO) { repository.getUser() }
        for(data in user){
            if(client.userId==data.userId&&client.userPw==data.userPw){
                mutableLogFlag.value=1
                Toast.makeText(app,succeedText,Toast.LENGTH_SHORT).show()
                return
            }
            else if(client.userId==data.userId&&client.userPw!=data.userPw){
                mutableLogFlag.value=2
                Toast.makeText(app,wrongText,Toast.LENGTH_SHORT).show()
                return
            }
        }
        if(logFlag.value==0){
            Toast.makeText(app,notFoundText,Toast.LENGTH_SHORT).show()
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
}