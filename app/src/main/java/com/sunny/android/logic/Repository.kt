package com.sunny.android.logic

import android.util.Log
import androidx.lifecycle.liveData
import com.sunny.android.logic.dao.PlaceDao
import com.sunny.android.logic.model.Place
import com.sunny.android.logic.model.Weather
import com.sunny.android.logic.network.SunnyNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.lang.Exception
import java.lang.RuntimeException

/** Sunny com.sunny.android.logic
 * @Date: 2021/2/1 17:28
 * @Author: toPolaris
 * @Description: 仓库层，用于判断从本地还是网络获取数据
 */
object Repository {
    /** liveData会自动构建并返回一个LiveData对象，代码块中提供一个挂起函数的上下文 */
    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            //Log.d("Repository query", query)
            val placeResponse = SunnyNetwork.searchPlaces(query)
            //Log.d("Repository status", placeResponse.status)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                //Log.d("Repository places", places.toString())
                Result.success(places)
            } else {
                Result.failure(RuntimeException("Response Status is ${placeResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Place>>(e)
        }
        //Log.d("Repository result", result.toString())
        emit(result)
    }

    fun refreshWeather(lng: String, lat: String) = liveData(Dispatchers.IO) {
        val result = try {
            Log.d("Repository ", "refreshWeather lng,lat ${lng},${lat}")
            coroutineScope {
                val deferredRealtime = async {
                    SunnyNetwork.getRealtimeWeather(lng, lat)
                }
                val deferredDaily = async {
                    SunnyNetwork.getDailyWeather(lng, lat)
                }
                val realtimeResponse = deferredRealtime.await()
                val dailyResponse = deferredDaily.await()
                if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
                    val weather =
                        Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
                    Result.success(weather)
                } else {
                    Result.failure(RuntimeException("Realtime Response Status is ${realtimeResponse.status}, Daily Response Status is ${dailyResponse.status}"))
                }
            }
        } catch (e: Exception) {
            Result.failure<Weather>(e)
        }
        emit(result)
    }

    fun savePlace(place: Place) = PlaceDao.savePlace(place)

    fun getSavedPlace() = PlaceDao.getSavedPlace()

    fun isPlaceSaved() = PlaceDao.isPlaceSaved()

}