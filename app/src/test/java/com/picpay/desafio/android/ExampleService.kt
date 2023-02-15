package com.picpay.desafio.android

import com.picpay.desafio.android.data.datasource.remote.PicPayService
import com.picpay.desafio.android.data.model.UserResponse

class ExampleService(
    private val service: PicPayService
) {

    fun example(): List<UserResponse> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}