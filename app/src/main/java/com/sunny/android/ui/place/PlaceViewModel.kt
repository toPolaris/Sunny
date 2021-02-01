package com.sunny.android.ui.place

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sunny.android.logic.Repository
import com.sunny.android.logic.model.Place

/** Sunny com.sunny.android.ui.place
 * @Date: 2021/2/1 17:42
 * @Author: toPolaris
 * @Description: place界面的ViewModel层
 */
class PlaceViewModel : ViewModel() {
    private val searchLiveData = MutableLiveData<String>()
    val placeList = ArrayList<Place>() // 用来缓存界面上用来显示的位置数据
    val placeLiveData = Transformations.switchMap(searchLiveData) { query ->
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query: String) {
        searchLiveData.value = query
        //Log.d("PlaceViewModel", searchLiveData.value.toString())
    }
}