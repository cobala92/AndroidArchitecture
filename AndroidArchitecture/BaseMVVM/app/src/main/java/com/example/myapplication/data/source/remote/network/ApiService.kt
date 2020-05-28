package com.example.myapplication.data.source.remote.network

import com.example.myapplication.data.source.remote.response.LoginResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by PhuongDang on 5/26/20
 */
interface ApiService {
    @GET("login")
    fun login(@Query("email") email: String, @Query("password") password: String): Single<LoginResponse>
}
