package com.picpay.desafio.android.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.domain.model.response.User
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_user.view.name
import kotlinx.android.synthetic.main.list_item_user.view.picture
import kotlinx.android.synthetic.main.list_item_user.view.progressBar
import kotlinx.android.synthetic.main.list_item_user.view.root
import kotlinx.android.synthetic.main.list_item_user.view.username
import java.lang.Exception

class UserListItemViewHolder(itemView: View, private val onItemClick: (User) -> Unit) : RecyclerView.ViewHolder
    (itemView) {

    private var _binding: ListItemUserBinding? = null
    private val binding get() = _binding


    fun bind(user: User) {
        itemView.setOnClickListener {
            onItemClick(user)
        }
        itemView.name.text = user.name
        itemView.username.text = user.username
        itemView.progressBar.isVisible = false
        Picasso.get()
            .load(user.img)
            .error(R.drawable.ic_round_account_circle)
            .into(itemView.picture, object : Callback {
                override fun onSuccess() {
                    itemView.progressBar.isVisible = false
                }

                override fun onError(e: Exception?) {
                    itemView.progressBar.isVisible = false
                }
            })

    }

    companion object {
        fun build(parent: ViewGroup, onItemClick: (User) -> Unit): UserListItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.list_item_user, parent, false)
            return UserListItemViewHolder(view, onItemClick)
        }
    }
}
