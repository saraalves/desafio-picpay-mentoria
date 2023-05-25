package com.picpay.desafio.android.data.remote.datasource

import com.picpay.desafio.android.data.extensions.parseHttpError
import com.picpay.desafio.android.data.mapper.UserResponseToModelMapper
import com.picpay.desafio.android.data.remote.datasource.service.UserApi
import com.picpay.desafio.android.domain.model.response.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRemoteDataSourceImpl(
    private val userApi: UserApi,
    private val userMapper: UserResponseToModelMapper
) : UserRemoteDataSource {
    override fun getUsers(): Flow<List<User>> {
        return flow { emit(userMapper.map(userApi.getUsers())) }.parseHttpError()
    }
}
