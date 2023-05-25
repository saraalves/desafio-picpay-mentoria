package com.picpay.desafio.android.presentation.resourceprovider

import android.graphics.drawable.Drawable
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes resId: Int, vararg params: String = emptyArray()): String
    fun getColor(colorId: Int): Int
    fun getDrawable(@DrawableRes drawableId: Int): Drawable?
    fun getDimension(@DimenRes resId: Int): Float
    fun getDimensionPixelSize(@DimenRes resId: Int): Int
    fun getQuantityString(@PluralsRes id: Int, quantity: Int, vararg formatArgs: Any): String
    fun getIdentifier(name: String, resourceType: String): Int
}