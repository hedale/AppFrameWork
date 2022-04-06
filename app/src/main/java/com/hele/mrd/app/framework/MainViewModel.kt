package com.hele.mrd.app.framework

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.hele.mrd.app.lib.base.BaseApp
import com.hele.mrd.app.lib.base.BaseViewModel
import com.hele.mrd.app.lib.base.dataclass.BaseLiveDataDto
import com.hele.mrd.app.lib.base.livedata.SingleLiveEvent
import com.hele.mrd.app.lib.cache.CacheFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(application: Application): BaseViewModel<MainApiService>(application) {
    override fun createApiServiceType(): Class<MainApiService> {
        return MainApiService::class.java
    }

    fun fetchData(showLoading: Boolean = false){
        viewModelScope.launch {
            try {
                if(showLoading) {
                    showLoading()
                }
                delay(3000)
                val resp = apiService.fetchData()
                appComponent.cacheManager.put("resp",resp)
                respLiveData.postValue(BaseLiveDataDto(resp))
            }catch (e: Exception){
                e.printStackTrace()
                respLiveData.postValue(BaseLiveDataDto(e = e))
            }finally {
                if(showLoading) {
                    hideLoading()
                }
            }
        }
    }

    val respLiveData by lazy {
        SingleLiveEvent<BaseLiveDataDto<String>>()
    }
}