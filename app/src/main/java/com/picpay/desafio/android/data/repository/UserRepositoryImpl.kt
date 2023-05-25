package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.extensions.parseHttpError
import com.picpay.desafio.android.data.local.UserLocalDataSource
import com.picpay.desafio.android.data.remote.datasource.UserRemoteDataSource
import com.picpay.desafio.android.domain.model.response.User
import com.picpay.desafio.android.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
) : UserRepository {
    override fun getUser(): Flow<List<User>> = flow {
        // aqui que a gente faz tratamento pro erro de conecção
        getUserData().catch { error ->
            fetchUserLocalData().collect {
                if(it.isEmpty()) { throw error } else { emit(it)}
            }
         }.collect { remoteList ->
            if (userLocalDataSource.getUsers() == remoteList) {
                fetchUserLocalData().collect { emit(it) }
            } else {
                registerUserListDb(remoteList)
                emit(remoteList)
            }
        }

    }

    override fun getUserData(): Flow<List<User>> = userRemoteDataSource.getUsers()


    private suspend fun registerUserListDb(data: List<User>) = userLocalDataSource.saveUsers(data)

    override fun fetchUserLocalData(): Flow<List<User>> = flow { emit(userLocalDataSource.getUsers())  }

    private fun deleteUserDb(id: String) = userLocalDataSource.deleteUser(id)

    private fun deleteUsersDb() = userLocalDataSource.deleteUsers()

}

