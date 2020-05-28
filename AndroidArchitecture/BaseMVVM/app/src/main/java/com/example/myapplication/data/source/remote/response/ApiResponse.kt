package com.example.myapplication.data.source.remote.response

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.JsonParseException
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
/**
 * Created by PhuongDang on 5/27/20
 */
internal const val UNKNOWN_CODE = -1
internal const val NETWORK_ERROR = 1000

sealed class ApiResponse<T> {
    companion object {
        fun <T> create(response: Response<T>): ApiResponse<T> {
            if (response.isSuccessful && response.body() != null) {
                response.body()?.let {
                    return ApiSuccessResponse(it)
                }
            } else {
                try {
                    val gson = GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .create()
                    return gson.fromJson<ApiErrorResponse<T>>(
                        response.errorBody()?.charStream(),
                        ApiErrorResponse::class.java
                    )
                } catch (ex: JsonParseException) {
                    println(ex.toString())
                }
            }
            return ApiErrorResponse(
                response.code(),
                response.errorBody()?.string() ?: response.message())
        }

        fun <T> create(errorCode: Int, error: Throwable): ApiErrorResponse<T> {
            return when (error) {
                is SocketTimeoutException, is ConnectException, is UnknownHostException -> {
                    ApiErrorResponse(NETWORK_ERROR, "")
                }
                else -> ApiErrorResponse(errorCode, error.message ?: "Unknown Error!")
            }
        }
    }
}

data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()

data class ApiErrorResponse<T>(
    @SerializedName("code") val code: Int, @SerializedName("message") val message: String) : ApiResponse<T>()
