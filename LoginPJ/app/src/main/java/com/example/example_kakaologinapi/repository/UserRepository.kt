package com.example.example_kakaologinapi.repository

import com.example.example_kakaologinapi.database.User
import com.example.example_kakaologinapi.retrofit.DTO.RfcOpenApi
import retrofit2.Response

interface UserRepository{ // repository에 네트워크통신(api)과 내장데이터베이스통신 을 추상화함
    //로그인 데이터베이스

    abstract suspend fun getUser():List<User>
    abstract suspend fun addUser(user: User)
    abstract suspend fun deleteUser(user: User)

    //api 호출
    abstract suspend fun getRestaurantData(key:String,city:String): Response<RfcOpenApi>
}

