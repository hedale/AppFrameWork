package com.hele.mrd.app.framework

import com.hele.mrd.app.lib.api.ApiManager
import com.hele.mrd.app.lib.base.BaseApp
import com.hele.mrd.app.lib.base.createLoading
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainApp: BaseApp() {
    override fun setupApiManager(): ApiManager {
        return ApiManager.build {
            baseUrl("https://www.baidu.com")
            addConverterFactory(ScalarsConverterFactory.create())
        }
    }

    override fun createLoading(): createLoading {
        return {
            MainLoading.newInstance(it)
        }
    }

}