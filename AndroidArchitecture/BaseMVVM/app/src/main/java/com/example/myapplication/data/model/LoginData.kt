package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by PhuongDang on 5/26/20
 */
data class LoginData(
    @SerializedName("page") internal val page: Int,
    @SerializedName("total") internal val total: Int,
    @SerializedName("data") internal val data: List<User>
)

data class User(
    @SerializedName("id") internal val id: Int,
    @SerializedName("name") internal val name: String,
    @SerializedName("year") internal val year: Int,
    @SerializedName("color") internal val color: String,
    @SerializedName("pantone_value") internal val pantoneValue: String
)
