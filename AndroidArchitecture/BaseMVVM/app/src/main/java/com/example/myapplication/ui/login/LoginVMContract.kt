package com.example.myapplication.ui.login

import com.example.myapplication.data.source.remote.response.LoginResponse
import io.reactivex.Single

/**
 * Created by PhuongDang on 5/27/20
 */
interface LoginVMContract {
    fun login(email: String, password: String): Single<LoginResponse>
}
