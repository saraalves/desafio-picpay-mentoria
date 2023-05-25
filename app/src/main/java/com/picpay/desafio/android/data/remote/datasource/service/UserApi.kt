package com.picpay.desafio.android.data.remote.datasource.service

import retrofit2.http.GET

interface UserApi {

    @GET("users")
    suspend fun getUsers(): List<UserResponse>
}
