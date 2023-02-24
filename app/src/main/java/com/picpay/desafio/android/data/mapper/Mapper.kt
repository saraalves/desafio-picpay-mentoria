package com.picpay.desafio.android.data.mapper

interface Mapper<S, T> {
    fun map(source: S): T
}