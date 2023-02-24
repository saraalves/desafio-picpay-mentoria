package com.picpay.desafio.android.data.remote.datasource

import com.picpay.desafio.android.data.mapper.UserResponseToModelMapper
import com.picpay.desafio.android.data.remote.api.UserApi
import com.picpay.desafio.android.domain.model.response.User

class UserRemoteDataSourceImpl(
    private val userApi: UserApi,
    private val userMapper: UserResponseToModelMapper
): UserRemoteDataSource {
    override suspend fun getUser(): List<User> {
        return userMapper.map(userApi.getUsers())
    }
}
