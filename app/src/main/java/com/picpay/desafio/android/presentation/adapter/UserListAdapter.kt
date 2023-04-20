package com.picpay.desafio.android.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.picpay.desafio.android.domain.model.response.User

class UserListAdapter() : ListAdapter<User, UserListItemViewHolder>(DefaultDiffCallback<User>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemViewHolder {
        return UserListItemViewHolder.build(parent)
    }

    override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) {
        holder.bind(currentList[position] as User)
    }
}