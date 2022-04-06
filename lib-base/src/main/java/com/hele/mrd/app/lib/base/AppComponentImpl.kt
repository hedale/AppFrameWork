package com.hele.mrd.app.lib.base

import android.app.Application
import androidx.viewbinding.ViewBinding
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hele.mrd.app.lib.api.ApiManager
import com.hele.mrd.app.lib.api.ApiServiceCacheManager
import com.hele.mrd.app.lib.base.view.ILoading
import com.hele.mrd.app.lib.cache.CacheFactory

typealias createLoading = (content: String?) -> ILoading<out ViewBinding>

class AppComponentImpl(private val application: Application, private val apiManager: ApiManager) {

    companion object {
        inline fun build(block: Builder.() -> Unit): AppComponentImpl {
            return Builder().apply(block).build()
        }
    }

    constructor(builder: Builder) : this(builder.application, builder.apiManager){
        ApiServiceCacheManager.apiManager = apiManager
        this.createLoading = builder.createLoading
        this.gson = builder.gson
    }

    lateinit var createLoading: createLoading

    lateinit var gson: Gson

    val cacheManager by lazy {
        CacheFactory.createApplicationCache(application)
    }

    class Builder {

        lateinit var application: Application

        lateinit var apiManager: ApiManager

        lateinit var createLoading: createLoading

        lateinit var gson: Gson

        fun application(application: Application) {
            this.application = application
        }

        fun apiManager(apiManager: ApiManager) {
            this.apiManager = apiManager
        }

        fun createLoading(createLoading: createLoading){
            this.createLoading = createLoading
        }

        fun gson(gson: Gson){
            this.gson = gson
        }

        fun build() = AppComponentImpl(this)
    }




}