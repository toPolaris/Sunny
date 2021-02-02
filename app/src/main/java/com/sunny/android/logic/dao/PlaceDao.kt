package com.sunny.android.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.sunny.android.SunnyApplication
import com.sunny.android.logic.model.Place

/** Sunny com.sunny.android.logic.dao
 * @Date: 2021/2/2 16:54
 * @Author: toPolaris
 * @Description: 储存搜索记录
 */
object PlaceDao {
    fun savePlace(place: Place) {
        sharedPreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }

    fun getSavedPlace(): Place {
        val placeJson = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")
    private fun sharedPreferences() =
        SunnyApplication.context.getSharedPreferences("sunny_weather", Context.MODE_PRIVATE)
}