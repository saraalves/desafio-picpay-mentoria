package com.picpay.desafio.android.presentation.error

import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.exception.ConnectionError
import com.picpay.desafio.android.presentation.resourceprovider.ResourceProvider

internal fun Throwable.mapToError(resource: ResourceProvider): ErrorScreen = when (this) {
    is ConnectionError -> ErrorScreen(
        errorTitle = resource.getString(R.string.connection_error_title),
        errorMessage = resource.getString(R.string.connection_error_msg),
        errorIllus = R.mipmap.error_connection_3,
        primaryButtonDescription = resource.getString(R.string.withou_connection_try_again_button),
        secondaryButtonDescription = resource.getString(R.string.without_connection_try_later_btn)
    )
    else -> ErrorScreen(
        errorTitle = resource.getString(R.string.something_wrong_title),
        errorMessage = resource.getString(R.string.without_connection_error_msg),
        errorIllus = R.mipmap.error_generico_1,
        primaryButtonDescription = resource.getString(R.string.withou_connection_try_again_button),
        secondaryButtonDescription = resource.getString(R.string.without_connection_try_later_btn)
    )
}