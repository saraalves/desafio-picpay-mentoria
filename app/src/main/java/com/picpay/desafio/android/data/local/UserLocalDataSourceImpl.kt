package com.picpay.desafio.android.data.local

import com.picpay.desafio.android.data.mapper.UserCacheToDomainMapper
import com.picpay.desafio.android.data.mapper.UserDomainToCacheMapper
import com.picpay.desafio.android.domain.model.response.User

class UserLocalDataSourceImpl(
    private val userDao: UserDao,
    private val userDomainToCacheMapper: UserDomainToCacheMapper,
    private val userCacheToDomainMapper: UserCacheToDomainMapper
    ): UserLocalDataSource {
    override suspend fun saveUsers(userEntityList: List<User>) {
        userDao.saveUsers(userEntityList.map { userDomainToCacheMapper.map(it) })
    }

    override suspend fun getUsers(): List<User> {
       return userDao.getUsers().map {
            userCacheToDomainMapper.map(it)
        }
    }

    override suspend fun deleteUser(id: Int) {
        userDao.deleteUser(id)
    }
}