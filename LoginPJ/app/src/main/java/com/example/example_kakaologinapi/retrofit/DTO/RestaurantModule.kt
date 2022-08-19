package com.example.example_kakaologinapi.retrofit.DTO

import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RestaurantModule { // Retrofit 라이브러리로 API를 클라이언트로 내려받으려면, 베이스주소, retrofit객체, Iretrofit객체, okhttpClient객체가 필요

    @Singleton
    @Provides
    fun provideBaseURL():String{
        return "http://apis.data.go.kr/6310000/ulsanrestaurant/"
    }

    @Singleton
    @Provides
    fun provideInterceptor():HttpLoggingInterceptor{
        return HttpLoggingInterceptor().apply {
            level=HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor):OkHttpClient{
        return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
    }

    @Singleton
    @Provides
    fun provideTikXml():TikXml{
        return TikXml.Builder().exceptionOnUnreadXml(false).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient,tikXml: TikXml):Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(TikXmlConverterFactory.create(tikXml))
            .baseUrl("http://apis.data.go.kr/6310000/ulsanrestaurant/")
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun createRestaurantService(retrofit: Retrofit):RestaurantService{
        return retrofit.create(RestaurantService::class.java)
    }

    /*
    private val baseURL="http://apis.data.go.kr/6310000/ulsanrestaurant/" // 베이스주소

    private val okHttpClient by lazy {
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level=HttpLoggingInterceptor.Level.BODY
        }).build()
    }

    private val retrofit by lazy { // retrofit객체에는 베이스주소,client,converter를 등록해서 build()
        Retrofit.Builder()
            .addConverterFactory(TikXmlConverterFactory.create(TikXml.Builder().exceptionOnUnreadXml(false).build()))
            .baseUrl("http://apis.data.go.kr/6310000/ulsanrestaurant/")
            .client(okHttpClient)
            .build()
    }

    val iRetrofit by lazy { // iretrofit객체를 통해 만들어진 @GET문장으로 데이터 요청후 반환받음
        retrofit.create(RestaurantService::class.java)
    }*/
}