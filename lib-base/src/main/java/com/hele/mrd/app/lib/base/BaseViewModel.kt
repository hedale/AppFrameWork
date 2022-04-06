package com.hele.mrd.app.lib.base

import android.app.Application
import androidx.lifecycle.*
import com.hele.mrd.app.lib.api.ApiService
import com.hele.mrd.app.lib.api.ApiServiceCacheManager
import com.hele.mrd.app.lib.base.livedata.SingleLiveEvent
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

open class BaseViewModel<T : ApiService>(application: Application) : AndroidViewModel(application), DefaultLifecycleObserver {

    val apiService: T by lazy {
        ApiServiceCacheManager.getApiService(application, createApiServiceType())
    }

    protected val appComponent by lazy {
        getApplication<BaseApp>().appComponent
    }

    private val json by lazy {
        "application/json; charset=UTF-8".toMediaType()
    }


    val baseUI: UI by lazy { UI() }

    open fun createApiServiceType(): Class<T>{
        return ApiService::class.java as Class<T>
    }

    fun showLoading(content: String = "加载中"){
        baseUI.loading.postValue(true to content)
    }

    fun hideLoading(){
        baseUI.loading.postValue(false to null)
    }

    fun toast(content: String?){
        baseUI.toast.postValue(content)
    }

    protected fun genBody(params: Any): RequestBody {
        return appComponent.gson.toJson(params).toRequestBody(json)
    }

    class UI {
        val loading by lazy {
            SingleLiveEvent<Pair<Boolean,String?>>()
        }

        val toast by lazy {
            SingleLiveEvent<String>()
        }
    }

}