package com.picpay.desafio.android.domain.exception

import java.io.IOException

private const val NO_INTERNET_CONNECTION_MESSAGE = "Verifique sua conexão, e tente novamente."

class ConnectionError(override val message: String = NO_INTERNET_CONNECTION_MESSAGE) : IOException()