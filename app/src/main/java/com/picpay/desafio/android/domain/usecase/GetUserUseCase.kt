package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.domain.model.response.User
import com.picpay.desafio.android.domain.repository.UserRepository

class GetUserUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): List<User> {
        return repository.getUser()
    }
}