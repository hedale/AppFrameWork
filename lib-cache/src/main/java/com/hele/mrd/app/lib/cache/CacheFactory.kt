package com.hele.mrd.app.lib.cache

import android.app.Application

object CacheFactory {

    private lateinit var cacheManager: CacheManager<String,Any>

    fun createApplicationCache(context: Application): CacheManager<String,Any>{
        if(!this::cacheManager.isInitialized){
            cacheManager = CacheManager(100,context)
        }
        return cacheManager
    }
}