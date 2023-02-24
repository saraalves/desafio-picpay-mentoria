package com.picpay.desafio.android.data.remote.api

import com.picpay.desafio.android.data.remote.model.UserResponse
import retrofit2.http.GET

interface UserApi {

    @GET("users")
    suspend fun getUsers(): List<UserResponse>
}