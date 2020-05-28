package com.example.myapplication.data.source.remote.network

import okhttp3.ResponseBody
import retrofit2.*
import java.lang.reflect.Type
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by PhuongDang on 5/27/20
 */
class RxCallAdapterWrapper<R>(type: Type, retrofit: Retrofit, wrapped: CallAdapter<R, *>?) :
    BaseRxCallAdapterWrapper<R>(type, retrofit, wrapped) {

    override fun convertRetrofitExceptionToCustomException(throwable: Throwable, retrofit: Retrofit): Throwable {
        if (throwable is HttpException) {
            val converter: Converter<ResponseBody, ApiException> =
                retrofit.responseBodyConverter(ApiException::class.java, arrayOfNulls<Annotation>(0))
            val response: Response<*>? = throwable.response()
            if (response != null) {
                return if (response.errorBody() != null) {
                    try {
                        val apiException = converter.convert(response.errorBody())
                        if (apiException != null) {
                            apiException.statusCode = response.code()
                            apiException
                        } else getApiExceptionWhenErrorBodyNull(response.code())
                    } catch (e: Exception) {
                        getApiExceptionWhenErrorBodyNull(response.code())
                    }
                } else {
                    getApiExceptionWhenErrorBodyNull(response.code())
                }
            }
        }

        // Handle case error not connect to internet
        if (throwable is UnknownHostException || throwable is ConnectException) {
            // Set message error of this case in activity extension
            val apiException = ApiException(mutableListOf())
            apiException.statusCode = ApiException.NETWORK_ERROR_CODE
//            RxBus.publish(ConnectInternetEvent())
            return apiException
        }

        // Handle case client timeout
        if (throwable is SocketTimeoutException) {
            val apiException = ApiException(mutableListOf())
            apiException.statusCode = HttpURLConnection.HTTP_CLIENT_TIMEOUT
            return apiException
        }

        return throwable
    }

    private fun getApiExceptionWhenErrorBodyNull(responseCode: Int): ApiException {
        val bodyErrorException = ApiException(mutableListOf())
        bodyErrorException.statusCode = responseCode
        return bodyErrorException
    }
}
