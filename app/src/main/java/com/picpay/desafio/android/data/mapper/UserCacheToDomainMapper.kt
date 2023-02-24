package com.picpay.desafio.android.data.mapper

import com.picpay.desafio.android.data.remote.model.UserEntity
import com.picpay.desafio.android.domain.model.response.User

class UserCacheToDomainMapper : Mapper<UserEntity, User> {
    override fun map(source: UserEntity): User = User(
            id = source.id,
            name = source.name,
            username = source.username,
            img = source.img
        )
}