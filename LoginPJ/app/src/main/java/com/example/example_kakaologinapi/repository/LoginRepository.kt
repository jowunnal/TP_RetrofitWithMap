package com.example.example_kakaologinapi.repository

import android.annotation.SuppressLint
import android.content.Context
import com.example.example_kakaologinapi.database.LoginDao
import com.example.example_kakaologinapi.database.LoginDatabase
import com.example.example_kakaologinapi.database.User
import com.example.example_kakaologinapi.retrofit.DTO.IRetrofit
import com.example.example_kakaologinapi.retrofit.DTO.RestaturantRetrofit

class LoginRepository(context:Context) { // repository에 네트워크통신(api)과 내장데이터베이스통신 을 추상화함
    //로그인 데이터베이스
    private val loginDao=LoginDatabase.getInstance(context).loginDao
    suspend fun getUser() = loginDao.getUser()
    suspend fun addUser(user: User)=loginDao.addUser(user)
    suspend fun deleteUser(user: User)=loginDao.deleteUser(user)

    //api 호출
    suspend fun getRestaurantData(key:String,city:String)= RestaturantRetrofit.iRetrofit.getData(key,city)

    //repository 의 싱글톤 인스턴스
    companion object{
        private var instance : LoginRepository ?= null

        fun getInstance(context: Context):LoginRepository{
            instance?: synchronized(LoginRepository::class.java){
                val rInstance = LoginRepository(context)
                instance=rInstance
                rInstance
            }
            return instance!!
        }
    }
}