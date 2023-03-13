package com.picpay.desafio.android.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.model.response.User
import com.picpay.desafio.android.domain.usecase.GetUserUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class UserViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    var users: LiveData<List<User>> = _users

    private val _loading = MutableLiveData<Boolean>()
    var loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<Int>()
    var error: LiveData<Int> = _error


    fun getUsers() {
        viewModelScope.launch {
            getUserUseCase()
                .flowOn(dispatcher)
                .onStart { _loading.value = true }
                .catch { _error.value = R.string.error }
                .onCompletion { _loading.value = false }
                .collect { _users.value = it }
        }
    }

    // função pra tratar erro do users
    // colocar tela de detalhes
    // terminar o data source local
    // adicionar os fragments um pra lista e outro pra tela de detalhes
    // colocar telas de erro com ilustração
    // adicionar testes unitários

}