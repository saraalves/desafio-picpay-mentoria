package com.picpay.desafio.android.data.mapper

import com.picpay.desafio.android.data.remote.model.UserEntity
import com.picpay.desafio.android.domain.model.response.User

class UserDomainToCacheMapper : Mapper<User, UserEntity> {
    override fun map(source: User): UserEntity = UserEntity(
            id = source.id,
            name = source.name,
            username = source.username,
            img = source.img
        )
}