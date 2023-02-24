package com.picpay.desafio.android.data.remote.datasource

import com.picpay.desafio.android.domain.model.response.User

interface UserRemoteDataSource {
    suspend fun getUser(): List<User>
}