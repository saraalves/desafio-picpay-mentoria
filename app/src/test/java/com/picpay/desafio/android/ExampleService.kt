package com.picpay.desafio.android

import com.picpay.desafio.android.data.remote.api.UserApi
import com.picpay.desafio.android.data.remote.model.UserResponse

class ExampleService(
    private val service: UserApi
) {

    fun example(): List<UserResponse> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}