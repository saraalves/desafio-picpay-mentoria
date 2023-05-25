package com.picpay.desafio.android.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.picpay.desafio.android.domain.model.response.User

class DefaultDiffCallback<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }


}

//class DefaultDiffCallback<T> : DiffUtil.ItemCallback<T>() {
//
//    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
//        return oldItem == newItem
//    }
//
//    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
//        return areItemsTheSame(oldItem, newItem)
//    }
//}
