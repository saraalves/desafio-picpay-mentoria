package com.picpay.desafio.android.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityUserListBinding

class UserListActivity : AppCompatActivity(R.layout.activity_user_list) {

    private val binding by lazy {
        ActivityUserListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}
