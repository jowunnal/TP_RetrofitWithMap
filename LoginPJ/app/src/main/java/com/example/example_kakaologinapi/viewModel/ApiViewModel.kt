package com.example.example_kakaologinapi.viewModel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.example_kakaologinapi.R
import com.example.example_kakaologinapi.repository.LoginRepository
import com.example.example_kakaologinapi.retrofit.DTO.List
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint

class ApiViewModel(private val app: Application) : AndroidViewModel(app) {
    private val repository=LoginRepository.getInstance(app.applicationContext)
    private var mutablelist = MutableLiveData<ArrayList<List>>()
    val restaurantList get() = mutablelist

    fun getRestaurantData(city:String){
        viewModelScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) { repository.getRestaurantData(app.getString(R.string.key)
                ,city) }
            if(response.isSuccessful){
                val result=response.body()?.body?.data?.list
                result.let{
                    if(it.isNullOrEmpty())
                        Toast.makeText(app.applicationContext,"검색된 식당이 없습니다.",Toast.LENGTH_SHORT).show()
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