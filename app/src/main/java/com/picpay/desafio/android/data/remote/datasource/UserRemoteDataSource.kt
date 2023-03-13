package com.picpay.desafio.android.data.remote.datasource

import com.picpay.desafio.android.domain.model.response.User
import kotlinx.coroutines.flow.Flow

interface UserRemoteDataSource {
    fun getUser(): Flow<List<User>>
}