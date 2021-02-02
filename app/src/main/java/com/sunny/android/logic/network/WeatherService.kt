package com.sunny.android.logic.network

import com.sunny.android.SunnyApplication
import com.sunny.android.logic.model.DailyResponse
import com.sunny.android.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/** Sunny com.sunny.android.logic.network
 * @Date: 2021/2/2 14:25
 * @Author: toPolaris
 * @Description: 一个用于查询实时天气和未来天气的Retrofit接口
 */
interface WeatherService {
    @GET("v2.5/${SunnyApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng") lng: String, @Path("lat") lat: String) : Call<RealtimeResponse>

    @GET("v2.5/${SunnyApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String) : Call<DailyResponse>
}