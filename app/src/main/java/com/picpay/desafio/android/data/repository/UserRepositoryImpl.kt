package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.extensions.parseHttpError
import com.picpay.desafio.android.data.local.UserLocalDataSource
import com.picpay.desafio.android.data.remote.datasource.UserRemoteDataSource
import com.picpay.desafio.android.domain.model.response.User
import com.picpay.desafio.android.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
) : UserRepository {
    override fun getUser(): Flow<List<User>> = flow {
        // trocar a ordem chamar primeiro a api pq se não vamos ter sempre uma lista do dao desatualizada
        fetchUserLocalData().collect {
            if (it.isEmpty()) {
                getUserData().collect { userRemoteList ->
                    userRemoteList.toList().apply {
                        registerUserListDb(this)
                        emit(this)
                    }
                }
            } else {
                emit(it)
            }
        }
    }

    override fun getUserData(): Flow<List<User>> = userRemoteDataSource.getUsers().parseHttpError()


    private suspend fun registerUserListDb(data: List<User>) = userLocalDataSource.saveUsers(data)

    override fun fetchUserLocalData(): Flow<List<User>> = userLocalDataSource.getUsers()

    private fun deleteUserDb(id: String) = userLocalDataSource.deleteUser(id)

    private fun deleteUsersDb() = userLocalDataSource.deleteUsers()

}

