package com.sunny.android.logic.network

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/** Sunny com.sunny.android.logic.network
 * @Date: 2021/2/1 16:46
 * @Author: toPolaris
 * @Description: 对所有的网络请求进行封装
 */
object SunnyNetwork {
    /** 使用ServiceCreator类创建PlaceService和WeatherService接口的动态代理对象 */
    private val placeService = ServiceCreator.create(PlaceService::class.java)
    private val weatherService = ServiceCreator.create(WeatherService::class.java)

    /** 定义挂起函数，并调用PlaceService中的searchPlaces函数再调用await使Retrofit发起网络请求 */
    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()
    suspend fun getRealtimeWeather(lng: String, lat: String) =
        weatherService.getRealtimeWeather(lng, lat).await()

    suspend fun getDailyWeather(lng: String, lat: String) =
        weatherService.getDailyWeather(lng, lat).await()

    /** 定义Call<T>的扩展函数await()来简化回调 */
    private suspend fun <T> Call<T>.await(): T {
        // 使用suspendCoroutine函数挂起当前协程
        return suspendCoroutine { continuation ->
            // 调用enqueue函数让Retrofit发起网络请求
            enqueue(object : Callback<T> {
                // 请求成功
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    // 判断返回的对象是否为空
                    if (body != null) {
                        Log.d("SunnyNetwork", "body ${body.toString()}")
                        continuation.resume(body)
                    } // suspendCoroutine返回body
                    else {
                        Log.d("SunnyNetwork", "null")
                        continuation.resumeWithException(RuntimeException("Response Body is Null"))
                    }
                }

                // 请求失败
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}