package com.example.myapplication.data.source.datasource

import com.example.myapplication.data.source.remote.response.LoginResponse
import io.reactivex.Single

/**
 * Created by PhuongDang on 5/26/20
 */
interface UserDataSource {
    fun loginUser(email: String, password: String): Single<LoginResponse>
}
