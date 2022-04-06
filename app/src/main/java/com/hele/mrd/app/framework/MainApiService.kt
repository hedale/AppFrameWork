package com.hele.mrd.app.framework

import com.hele.mrd.app.lib.api.ApiService
import retrofit2.http.GET

interface MainApiService: ApiService {

    @GET("")
    suspend fun fetchData(): String
}