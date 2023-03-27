package com.picpay.desafio.android.data.local

import com.picpay.desafio.android.domain.model.response.User
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {

    suspend fun saveUsers(userEntity: List<User>)
    fun getUsers(): Flow<List<User>>
    fun deleteUser(id: String)
    fun deleteUsers()
}