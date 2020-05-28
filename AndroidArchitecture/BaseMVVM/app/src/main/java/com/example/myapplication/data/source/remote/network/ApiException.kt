package com.example.myapplication.data.source.remote.network

import com.google.gson.annotations.SerializedName

/**
 * Created by PhuongDang on 5/27/20
 */
data class ApiException(@SerializedName("errors") val errors: MutableList<ApiErrorResponse>?) : Throwable() {

    companion object {
        internal const val NETWORK_ERROR_CODE = 700
        internal const val MAINTAIN_HEALTH_LIFE_ERROR_CODE = 428
    }

    var statusCode: Int? = null
}

data class ApiErrorResponse(
    @SerializedName("field") val field: String? = null,
    @SerializedName("code") val code: String? = null,
    @SerializedName("params") val params: MutableList<String>? = null,
    @SerializedName("default_message") val defaultMessage: String? = ""
)
