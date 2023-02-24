package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.local.UserLocalDataSource
import com.picpay.desafio.android.data.remote.datasource.UserRemoteDataSource
import com.picpay.desafio.android.domain.model.response.User
import com.picpay.desafio.android.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSourceImpl: UserLocalDataSource
) : UserRepository {
    override suspend fun getUser(): List<User> {
        return userRemoteDataSource.getUser()
    }

    private suspend fun deleteUsersDb(id: Int) {
        userLocalDataSourceImpl.deleteUser(id)
    }

    private suspend fun registerUserListDb(data: List<User>) {
        userLocalDataSourceImpl.saveUsers(data)
    }

    private suspend fun getUserListDb(): List<User> {
        return userLocalDataSourceImpl.getUsers()
    }
}