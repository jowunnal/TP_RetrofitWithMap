package com.example.example_kakaologinapi.viewModel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.example_kakaologinapi.database.User
import com.example.example_kakaologinapi.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class ManageMemberViewModel(val app: Application) : AndroidViewModel(app) {
    val repository= LoginRepository.getInstance(app.applicationContext)
    var mutableLogFlag = MutableLiveData(0)
    val logFlag: LiveData<Int> get() = mutableLogFlag
    val user by lazy { User("","","") }

    suspend fun checkUser(client: User, succeedText:String, wrongText:String, notFoundText:String) {
        val user = withContext(Dispatchers.IO) { repository.getUser() }
        for (data in user) {
            if (client.userId == data.userId && client.userPw == data.userPw) {
                mutableLogFlag.value = 1
                Toast.makeText(app.applicationContext, succeedText, Toast.LENGTH_SHORT).show()
                return
            } else if (client.userId == data.userId && client.userPw != data.userPw) {
                mutableLogFlag.value = 2
                Toast.makeText(app.applicationContext, wrongText, Toast.LENGTH_SHORT).show()
                return
            }
        }
        if (logFlag.value == 0) {
            Toast.makeText(app.applicationContext, notFoundText, Toast.LENGTH_SHORT).show()
        }
    }
}