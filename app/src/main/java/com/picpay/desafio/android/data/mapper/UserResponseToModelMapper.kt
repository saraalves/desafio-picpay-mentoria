package com.picpay.desafio.android.data.mapper

import com.picpay.desafio.android.data.remote.model.UserResponse
import com.picpay.desafio.android.domain.model.response.User

class UserResponseToModelMapper : Mapper<List<UserResponse>, List<User>> {
    override fun map(source: List<UserResponse>): List<User> = source.map { userResponse ->
        User(
            img = userResponse.img,
            name = userResponse.name,
            id = userResponse.id,
            username = userResponse.username
        )
    }
}