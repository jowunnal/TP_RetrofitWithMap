package com.example.example_kakaologinapi.viewModel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.example_kakaologinapi.R
import com.example.example_kakaologinapi.repository.UserRepositoryImpl
import com.example.example_kakaologinapi.retrofit.DTO.List
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import javax.inject.Inject

@HiltViewModel
class ApiViewModel @Inject constructor(private val context:Application, private val repository: UserRepositoryImpl) : ViewModel() {
    private var mutablelist = MutableLiveData<ArrayList<List>>()
    val restaurantList get() = mutablelist

    fun getRestaurantData(city:String){
        viewModelScope.launch(Dispatchers.IO) {
            val response = async{ repository.getRestaurantData(context.getString(R.string.key)
                ,city) }.await()
            if(response.isSuccessful){
                val result=response.body()?.body?.data?.list
                result.let{
                    if(it.isNullOrEmpty())
                        Toast.makeText(context.applicationContext,"검색된 식당이 없습니다.",Toast.LENGTH_SHORT).show()
                    else{
                        mutablelist.value= it as ArrayList<List>?
                    }
                }
            }
            else{
                Log.d("test","통신오류발생: "+response.errorBody())
            }
        }
    }
    fun makeMarker(list:List): MapPOIItem {
        val marker = MapPOIItem()
        val mapPoint= MapPoint.mapPointWithGeoCoord(list.lat!!,list.lng!!)
        marker.itemName = list.company
        marker.mapPoint = mapPoint
        marker.markerType = MapPOIItem.MarkerType.BluePin// 기본으로 제공하는 BluePin 마커 모양.
        marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        return marker
    }
}