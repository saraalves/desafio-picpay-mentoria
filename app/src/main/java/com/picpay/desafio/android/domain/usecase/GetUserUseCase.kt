package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.domain.model.response.User
import com.picpay.desafio.android.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUserUseCase(
    private val repository: UserRepository
) {
    operator fun invoke(): Flow<List<User>> = repository.getUser()
}