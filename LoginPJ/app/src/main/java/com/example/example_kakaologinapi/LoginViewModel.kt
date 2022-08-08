package com.example.example_kakaologinapi
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.example_kakaologinapi.database.LoginDatabase
import com.example.example_kakaologinapi.database.LoginRepository
import com.example.example_kakaologinapi.database.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginViewModel(app: Application) : AndroidViewModel(app) {
    private val loginDatabase = LoginDatabase.getInstance(app)
    private val repository= LoginRepository(loginDatabase.loginDao)
    var userLiveData= MutableLiveData(listOf(User("jinho","황진호","1234")))
    val userData get() = userLiveData


    fun getUser() = repository.getUser()

    fun addUser(id:String,pw:String){
        viewModelScope.launch(Dispatchers.IO) { repository.addUser(User(id,"jinho",pw)) }
        Log.d("test", "$id $pw")
    }

    fun deleteUser(user: User){
        viewModelScope.launch(Dispatchers.IO) { repository.deleteUser(user) }
    }
}