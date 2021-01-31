package com.sunny.android.logic.model

import com.google.gson.annotations.SerializedName

/** Sunny com.sunny.android.logic.model
 * @Date: 2021/1/31 17:14
 * @Author: toPolaris
 * @Description: 定义数据模型
 */
data class PlaceResponse(val status: String, val places: List<Place>)
data class Place(
    val name: String,
    val location: Location,
    @SerializedName("formatted_address") val address: String // 使用SerializedName注解使JSON字段与Kotlin字段建立映射关系
)

data class Location(val lng: String, val lat: String)
