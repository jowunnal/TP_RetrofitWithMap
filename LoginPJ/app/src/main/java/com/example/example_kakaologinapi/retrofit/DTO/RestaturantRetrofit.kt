package com.example.example_kakaologinapi.retrofit.DTO

import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object RestaturantRetrofit { // Retrofit 라이브러리로 API를 클라이언트로 내려받으려면, 베이스주소, retrofit객체, Iretrofit객체, okhttpClient객체가 필요
    private val baseURL="http://apis.data.go.kr/6310000/ulsanrestaurant/" // 베이스주소

    private val okHttpClient by lazy {
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level=HttpLoggingInterceptor.Level.BODY
        }).build()
    }

    private val retrofit by lazy { // retrofit객체에는 베이스주소,client,converter를 등록해서 build()
        Retrofit.Builder()
            .addConverterFactory(TikXmlConverterFactory.create(TikXml.Builder().exceptionOnUnreadXml(false).build()))
            .baseUrl(baseURL)
            .client(okHttpClient)
            .build()
    }

    val iRetrofit by lazy { // iretrofit객체를 통해 만들어진 @GET문장으로 데이터 요청후 반환받음
        retrofit.create(IRetrofit::class.java)
    }
}