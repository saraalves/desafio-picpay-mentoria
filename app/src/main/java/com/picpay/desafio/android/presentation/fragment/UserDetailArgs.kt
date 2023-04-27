package com.picpay.desafio.android.presentation.fragment

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserDetailArgs(
    val name: String,
    val username: String,
    val image: String
) : Parcelable
