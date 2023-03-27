package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.domain.model.response.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(): Flow<List<User>>
    fun getUserData(): Flow<List<User>>
    fun fetchUserLocalData(): Flow<List<User>>
}