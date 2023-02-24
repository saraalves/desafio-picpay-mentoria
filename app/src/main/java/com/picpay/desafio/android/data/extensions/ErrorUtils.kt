package com.picpay.desafio.android.data.extensions

import com.picpay.desafio.android.domain.exception.ConnectionError
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

internal fun Throwable.toRequestThrowable(): Throwable {
    return when (this) {
        is HttpException,
        is UnknownHostException,
        is TimeoutException,
        is InterruptedIOException,
        is SocketTimeoutException,
        is SocketException,
        is ConnectException -> ConnectionError()
        else -> this
    }
}