package com.picpay.desafio.android.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.model.response.User
import com.picpay.desafio.android.domain.usecase.GetUserUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(private val getUserUseCase: GetUserUseCase): ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    var users: LiveData<List<User>> = _users

    private val _loading = MutableLiveData<Boolean>()
    var loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<Int>()
    var error: LiveData<Int> = _error

    fun getUsers() {
        CoroutineScope(Dispatchers.Main).launch {
            val result = withContext(Dispatchers.IO) {
                getUserUseCase.invoke()
            }
            if (result.isEmpty()) {
                _error.value = R.string.error
            } else {
                _users.value = result
            }
            _loading.value = false
        }
    }
}