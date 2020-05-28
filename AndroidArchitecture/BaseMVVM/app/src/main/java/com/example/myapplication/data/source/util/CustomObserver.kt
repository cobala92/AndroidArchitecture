package com.example.myapplication.data.source.util

import androidx.lifecycle.Observer
import com.example.myapplication.data.source.remote.response.ApiErrorResponse
import com.example.myapplication.data.source.remote.response.ApiResponse
import com.example.myapplication.data.source.remote.response.ApiSuccessResponse

/**
 * Created by PhuongDang on 5/27/20
 */
class CustomObserver<T>(private val changeListener: ChangeListener<T>) : Observer<ApiResponse<T>> {
    override fun onChanged(t: ApiResponse<T>?) {
        (t as? ApiSuccessResponse)?.let {
            changeListener.onSuccess(it.body)
        }
        (t as? ApiErrorResponse)?.let {
            changeListener.onFailure(it)
        }
    }

    interface ChangeListener<T> {
        fun onSuccess(data: T)
        fun onFailure(apiErrorResponse: ApiErrorResponse<T>)
    }
}
