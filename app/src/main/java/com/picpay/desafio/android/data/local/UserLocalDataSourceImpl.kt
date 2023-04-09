package com.picpay.desafio.android.data.local

import com.picpay.desafio.android.data.mapper.UserCacheToDomainMapper
import com.picpay.desafio.android.data.mapper.UserDomainToCacheMapper
import com.picpay.desafio.android.domain.model.response.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class UserLocalDataSourceImpl(
    private val userDao: UserDao,
    private val userDomainToCacheMapper: UserDomainToCacheMapper,
    private val userCacheToDomainMapper: UserCacheToDomainMapper
    ): UserLocalDataSource {
    override suspend fun saveUsers(userEntityList: List<User>) {
        userDao.saveUsers(userEntityList.map { userDomainToCacheMapper.map(it) })
    }

    override fun getUsers(): List<User> {
        return userDao.getUsers().map {
                userCacheToDomainMapper.map(it)
            }
    }

    override fun deleteUser(id: String) {
        userDao.deleteUser(id)
    }

    override fun deleteUsers() {
        userDao.deleteUsers()
    }
}