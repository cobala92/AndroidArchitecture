package com.example.myapplication.ui.login

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.source.UserRepository
import com.example.myapplication.data.source.remote.response.LoginResponse
import io.reactivex.Single

/**
 * Created by PhuongDang on 5/27/20
 */
class LoginViewModel(private val userRepository: UserRepository) : LoginVMContract, ViewModel() {
    override fun login(email: String, password: String): Single<LoginResponse> {
        return userRepository.loginUser(email, password)
    }
}
