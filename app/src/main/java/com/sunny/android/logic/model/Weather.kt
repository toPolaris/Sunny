package com.sunny.android.logic.model

/** Sunny com.sunny.android.logic.model
 * @Date: 2021/2/2 14:22
 * @Author: toPolaris
 * @Description: 实时天气与未来天气的封装类
 */
data class Weather(val realtime: RealtimeResponse.Realtime, val daily: DailyResponse.Daily)
