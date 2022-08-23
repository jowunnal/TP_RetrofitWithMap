package com.example.example_kakaologinapi

import android.app.Application
import android.content.Context
import com.example.example_kakaologinapi.repository.UserRepositoryImpl
import com.example.example_kakaologinapi.retrofit.DTO.Item
import com.example.example_kakaologinapi.retrofit.DTO.RfcOpenApi
import com.example.example_kakaologinapi.viewModel.ApiViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class ApiViewModelTest {
    lateinit var apiViewModel: ApiViewModel

    @Before
    fun initialize(){
        val context=mock(Context::class.java)
        val repository=mock(UserRepositoryImpl::class.java)
        apiViewModel= ApiViewModel(context,repository)
    }

    @Test
    fun testLogic(){
//        val mapPOIItem=MapPOIItem()
//        val mapPoint=MapPoint.mapPointWithGeoCoord(12.5,22.5)
//        mapPOIItem.mapPoint=mapPoint
//        Mockito.`when`(apiViewModel.makeMarker(Item("","남구","음식점","",12.5,22.5,"",""))).thenReturn(
//            mapPOIItem)
        Mockito.`when`(apiViewModel).thenAnswer()
        runBlocking {
            apiViewModel.repository.getUser()
            verify(apiViewModel.repository, times(1)).getUser()
            assert(apiViewModel.context.isUiContext)
        }
    }
}