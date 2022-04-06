package com.hele.mrd.app.lib.api

import android.app.Application
import com.hele.mrd.app.lib.cache.CacheFactory
import retrofit2.Retrofit

object ApiServiceCacheManager {

    lateinit var apiManager: ApiManager

    private lateinit var retrofit : Retrofit

    private fun getRetrofit(application: Application): Retrofit{
        if(!this::retrofit.isInitialized){
            retrofit = apiManager.createRetrofit(application)
        }
        return retrofit
    }

    fun <T: ApiService> getApiService(application: Application,clazz: Class<T>): T{
        val cacheManager = CacheFactory.createApplicationCache(application)
        var apiService = cacheManager.get<Any>(clazz.name) as T?
        return if(apiService == null){
            apiService = getRetrofit(application).create(clazz)
            cacheManager.put(clazz.name,apiService)
            apiService
        }else{
            apiService
        }

    }

}