package com.example.myapplication.data.source.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Created by PhuongDang on 5/27/20
 */
open class BaseResponse(
    @SerializedName("success") internal var isSuccess: Boolean = false,
//    @SerializedName("error") internal val errors: MutableList<ApiErrorResponse> = mutableListOf()
    @SerializedName("error") internal val errors: MutableList<Any> = mutableListOf()
)
