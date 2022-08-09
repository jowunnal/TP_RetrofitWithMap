package com.example.example_kakaologinapi.retrofit.DTO

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IRetrofit {
    @GET("getulsanrestaurantList")
    suspend fun getData(@Query("serviceKey", encoded = true) key:String ,@Query("city") city:String) : Response<RfcOpenApi>
}