package com.hele.mrd.app.lib.api

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class ApiManager private constructor() {

    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    private val interceptors = arrayListOf<Interceptor>()

    private var connectTimeout = 15L

    private var readTimeout = 15L

    private var writeTimeout = 15L

    private lateinit var baseUrl: String

    private lateinit var factory: Converter.Factory

    fun createRetrofit(application: Application): Retrofit{
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .writeTimeout(writeTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
       if(BuildConfig.DEBUG){


               val chuckerCollector = ChuckerCollector(
                   context = application,
                   // Toggles visibility of the push notification
                   showNotification = true,
                   // Allows to customize the retention period of collected data
                   retentionPeriod = RetentionManager.Period.ONE_HOUR
               )

               val chuckerInterceptor = ChuckerInterceptor.Builder(application)
                   // The previously created Collector
                   .collector(chuckerCollector)
                   // The max body content length in bytes, after this responses will be truncated.
                   .maxContentLength(250_000L)
                   // List of headers to replace with ** in the Chucker UI
                   .redactHeaders("Auth-Token", "Bearer")
                   // Read the whole response body even when the client does not consume the response completely.
                   // This is useful in case of parsing errors or when the response body
                   // is closed before being read like in Retrofit with Void and Unit types.
                   .alwaysReadResponseBody(true)
                   .build()
           interceptors.add(chuckerInterceptor)

       }
        interceptors.forEach {
            okHttpClientBuilder.addInterceptor(it)
        }


        val builder = Retrofit.Builder().addConverterFactory(factory)
            .baseUrl(baseUrl).client(okHttpClientBuilder.build())

        return builder.build()
    }


    class Builder {

        private val interceptors = arrayListOf<Interceptor>()

        private var connectTimeout = 15L

        private var readTimeout = 15L

        private var writeTimeout = 15L

        private lateinit var baseUrl: String

        private lateinit var factory: Converter.Factory

        fun interceptors(interceptors: ArrayList<Interceptor>) {
            this.interceptors.addAll(interceptors)
        }

        fun interceptor(interceptor: Interceptor) {
            this.interceptors.add(interceptor)
        }

        fun connectTimeout(connectTimeout: Long) {
            this.connectTimeout = connectTimeout
        }

        fun readTimeout(readTimeout: Long) {
            this.readTimeout = readTimeout
        }

        fun writeTimeout(writeTimeout: Long) {
            this.writeTimeout = writeTimeout
        }

        fun baseUrl(url: String) {
            this.baseUrl = url
        }

        fun addConverterFactory(factory: Converter.Factory) {
            this.factory = factory
        }

        fun build(): ApiManager {
            val api = ApiManager()
            api.interceptors.addAll(interceptors)
            api.connectTimeout = connectTimeout
            api.readTimeout = readTimeout
            api.writeTimeout = writeTimeout
            api.baseUrl = baseUrl
            api.factory = factory

            return api
        }


    }

}