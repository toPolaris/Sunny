package com.sunny.android.logic.network

import com.sunny.android.SunnyApplication
import com.sunny.android.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/** Sunny com.sunny.android.logic.network
 * @Date: 2021/1/31 17:20
 * @Author: toPolaris
 * @Description: 定义一个用于访问彩云天气城市搜索API的Retrofit接口
 */
interface PlaceService {
    @GET("v2/place?token=${SunnyApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}