package com.example.myapplication.ui.login

import android.os.Bundle
import android.util.Log
import com.example.myapplication.R
import com.example.myapplication.data.source.UserRepository
import com.example.myapplication.extension.getViewModel
import com.example.myapplication.extension.observeOnUiThread
import com.example.myapplication.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by PhuongDang on 5/26/20
 */
class LoginActivity : BaseActivity() {

    private val viewModel by lazy {
        getViewModel {
            LoginViewModel(UserRepository())
        }
    }

    companion object {
        const val EMAIL = "eve.holt@reqres.in"
        const val PASSWORD = "cityslicka"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initListener()
    }

    private fun initListener() {
        btnLogin.setOnClickListener {
            viewModel.login(EMAIL, PASSWORD)
                .observeOnUiThread()
                .subscribe({data->
                    Log.d("TAG11", "data = ${data.data.page} == ${data.data.total} == ${data.data.data[0]}")

                }, {})
        }
    }

}
