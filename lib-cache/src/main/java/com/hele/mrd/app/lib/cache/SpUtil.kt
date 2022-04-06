package com.hele.mrd.app.lib.cache

import android.content.Context
import android.content.SharedPreferences

object SpUtil {

    fun getAppSp(context: Context): SharedPreferences {
        return context.getSharedPreferences("frame_work_cache_app", Context.MODE_PRIVATE)
    }

    fun getUserSp(context: Context): SharedPreferences {
        return context.getSharedPreferences("frame_work_cache_user", Context.MODE_PRIVATE)
    }

    fun putAppBoolean(context: Context, key: String, value: Boolean) {
        getAppSp(context).edit().putBoolean(key, value).apply()
    }

    fun putAppString(context: Context, key: String, value: String) {
        getAppSp(context).edit().putString(key, value).apply()
    }

    fun putAppInt(context: Context, key: String, value: Int) {
        getAppSp(context).edit().putInt(key, value).apply()
    }

    fun putAppFloat(context: Context, key: String, value: Float) {
        getAppSp(context).edit().putFloat(key, value).apply()
    }

    fun putAppLong(context: Context, key: String, value: Long) {
        getAppSp(context).edit().putLong(key, value).apply()
    }

    fun getAppBoolean(context: Context, key: String): Boolean {
        return getAppSp(context).getBoolean(key, false)
    }

    fun getAppString(context: Context, key: String): String? {
        return getAppSp(context).getString(key, null)
    }

    fun getAppInt(context: Context, key: String): Int {
        return getAppSp(context).getInt(key, -1)
    }

    fun getAppFloat(context: Context, key: String): Float {
        return getAppSp(context).getFloat(key, -1f)
    }

    fun getAppLong(context: Context, key: String): Long {
        return getAppSp(context).getLong(key, -1L)
    }


    fun putUserBoolean(context: Context, key: String, value: Boolean) {
        getUserSp(context).edit().putBoolean(key, value).apply()
    }

    fun putUserString(context: Context, key: String, value: String) {
        getUserSp(context).edit().putString(key, value).apply()
    }

    fun putUserInt(context: Context, key: String, value: Int) {
        getUserSp(context).edit().putInt(key, value).apply()
    }

    fun putUserFloat(context: Context, key: String, value: Float) {
        getUserSp(context).edit().putFloat(key, value).apply()
    }

    fun putUserLong(context: Context, key: String, value: Long) {
        getUserSp(context).edit().putLong(key, value).apply()
    }

    fun getUserBoolean(context: Context, key: String): Boolean {
        return getUserSp(context).getBoolean(key, false)
    }

    fun getUserString(context: Context, key: String): String? {
        return getUserSp(context).getString(key, null)
    }

    fun getUserInt(context: Context, key: String): Int {
        return getUserSp(context).getInt(key, -1)
    }

    fun getUserFloat(context: Context, key: String): Float {
        return getUserSp(context).getFloat(key, -1f)
    }

    fun getUserLong(context: Context, key: String): Long {
        return getUserSp(context).getLong(key, -1L)
    }


}