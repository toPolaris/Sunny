package com.sunny.android.logic.model

import com.google.gson.annotations.SerializedName

/** Sunny com.sunny.android.logic.model
 * @Date: 2021/2/2 14:08
 * @Author: toPolaris
 * @Description: 当日天气
 */
data class RealtimeResponse(val status: String, val result: Result) {
    data class Result(val realtime: Realtime)
    data class Realtime(
        val skycon: String,
        val temperature: Float,
        @SerializedName("air_quality") val airQuality: AirQuality
    )

    data class AirQuality(val aqi: AQI)
    data class AQI(val chn: Float)
}
