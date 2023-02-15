package com.picpay.desafio.android.data.datasource.remote

import com.picpay.desafio.android.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    fun getUsers(): Call<List<UserResponse>>
}