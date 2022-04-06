package com.hele.mrd.app.lib.base

import androidx.multidex.MultiDexApplication
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hele.mrd.app.lib.api.ApiManager
import com.hele.mrd.app.lib.cache.CacheFactory
import com.hele.mrd.app.lib.common.lifecycle.ActivityLifecycle

abstract class BaseApp: MultiDexApplication() {

    companion object{

        lateinit var app: BaseApp

    }

    lateinit var appComponent: AppComponentImpl

    protected val gson: Gson by lazy {
        GsonBuilder().create()
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        registerActivityLifecycleCallbacks(ActivityLifecycle())
        appComponent = AppComponentImpl.build {
            application(this@BaseApp)
            apiManager(setupApiManager())
            createLoading(createLoading())
            gson(this@BaseApp.gson)
        }
    }

    abstract fun setupApiManager(): ApiManager

    abstract fun createLoading(): createLoading
}