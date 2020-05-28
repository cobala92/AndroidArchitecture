package com.example.myapplication.data.source.remote

import com.example.myapplication.data.source.datasource.UserDataSource
import com.example.myapplication.data.source.remote.network.ApiClient
import com.example.myapplication.data.source.remote.network.ApiService
import com.example.myapplication.data.source.remote.response.LoginResponse
import io.reactivex.Single

/**
 * Created by PhuongDang on 5/26/20
 */
class UserRemoteDataSource(private val api: ApiService) : UserDataSource {

    constructor() : this(ApiClient.getInstance("https://reqres.in/api/").service)

    override fun loginUser(email: String, password: String): Single<LoginResponse> {
        return api.login(email, password)
    }
}
