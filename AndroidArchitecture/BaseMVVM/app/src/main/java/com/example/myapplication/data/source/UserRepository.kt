package com.example.myapplication.data.source

import com.example.myapplication.data.source.datasource.UserDataSource
import com.example.myapplication.data.source.remote.UserRemoteDataSource
import com.example.myapplication.data.source.remote.response.LoginResponse
import io.reactivex.Single

/**
 * Created by PhuongDang on 5/26/20
 */
class UserRepository : UserDataSource {

    private val userRemoteDataSource = UserRemoteDataSource()

    override fun loginUser(email: String, password: String): Single<LoginResponse> {
        return userRemoteDataSource.loginUser(email, password)
    }

}
