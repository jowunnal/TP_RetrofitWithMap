package com.example.example_kakaologinapi
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.example_kakaologinapi.database.LoginDatabase
import com.example.example_kakaologinapi.database.LoginRepository
import com.example.example_kakaologinapi.database.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(app: Application) : AndroidViewModel(app) {
    private val loginDatabase = LoginDatabase.getInstance(app)
    private val repository= LoginRepository(loginDatabase.loginDao)

    fun getUser(userId:String,userPw:String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user= withContext(Dispatchers.IO) { repository.getUser() }
            var logFlag=false
            for(data in user){
                if(userId==data.userId&&userPw==data.userPw){
                    logFlag=true
                    continue
                }
            }
            if(logFlag)
                Log.d("test","로그인 성공")
            else
                Log.d("test","아이디 또는 패스워드가 일치하지 않습니다.")
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