package com.sunny.android

/** Sunny com.sunny.android
 * @Date: 2021/1/31 17:02
 * @Author: toPolaris
 * @Description: 提供一种全局获取Context的方式
 */
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        const val TOKEN = "HELLO_WORLD" // 令牌值
    }

    override fun onCreate() {
        super.onCreate()
        /**
         * 此外还需要在AndroidManifest.xml中在<application>标签下指定android:name=".SunnyApplication"
         * 然后可以在项目的任何位置使用SunnyApplication.context获取Context对象
         * */
        context = applicationContext
    }


}