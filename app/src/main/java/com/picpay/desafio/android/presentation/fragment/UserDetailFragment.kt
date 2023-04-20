package com.picpay.desafio.android.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.FragmentUserDetailBinding

class UserDetailFragment : Fragment(R.layout.fragment_user_detail) {

    private var _binding: FragmentUserDetailBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }
}