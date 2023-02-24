package com.picpay.desafio.android.data.local

import com.picpay.desafio.android.domain.model.response.User

interface UserLocalDataSource {

    suspend fun saveUsers(userEntity: List<User>)
    suspend fun getUsers(): List<User>
    suspend fun deleteUser(id: Int)
}