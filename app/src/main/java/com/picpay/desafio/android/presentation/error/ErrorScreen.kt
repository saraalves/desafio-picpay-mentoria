package com.picpay.desafio.android.presentation.error

import androidx.annotation.DrawableRes

internal data class ErrorScreen(
    val errorTitle: String,
    val errorMessage: String,
    val primaryButtonDescription: String,
    val secondaryButtonDescription: String,
    @DrawableRes val errorIllus: Int
)