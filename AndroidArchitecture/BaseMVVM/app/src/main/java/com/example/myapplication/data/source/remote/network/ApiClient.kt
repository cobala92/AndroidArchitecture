package com.example.myapplication.data.source.remote.network

import com.example.myapplication.BuildConfig
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by PhuongDang on 5/26/20
 */
class ApiClient private constructor(url: String? = null) {

    private var baseUrl: String =
        if (url == null || url.isEmpty()) BuildConfig.BASE_API_URL else url

    companion object : SingletonHolder<ApiClient, String>(::ApiClient) {
        private const val API_TIMEOUT = 1L // 5 minutes
    }

    val service: ApiService
        get() {
            return createService()
        }

    private fun createService(): ApiService {
        val httpClientBuilder = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
        val client = httpClientBuilder
            .connectTimeout(API_TIMEOUT, TimeUnit.MINUTES)
            .writeTimeout(API_TIMEOUT, TimeUnit.MINUTES)
            .readTimeout(API_TIMEOUT, TimeUnit.MINUTES)
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val gs = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gs))
            .addCallAdapterFactory(CustomCallAdapterFactory())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}

open class SingletonHolder<out T, in A>(private var creator: (A?) -> T) {
    @kotlin.jvm.Volatile
    private var instance: T? = null

    /**
     * Generate instance for T class with argument A
     */
    fun getInstance(arg: A?): T {
        val i = instance
        if (i != null) {
            return i
        }

        return synchronized(this) {
            val i2 = instance
            if (i2 != null) {
                i2
            } else {
                val created = creator(arg)
                instance = created
                created
            }
        }
    }
}


