package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.domain.model.response.User

interface UserRepository {
    suspend fun getUser(): List<User>
}