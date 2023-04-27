package com.picpay.desafio.android.presentation.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.FragmentUserDetailBinding
import com.squareup.picasso.Picasso

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: UserDetailArgs? = arguments?.getParcelable("args")


        args?.let {
            binding?.name?.text = "Hi, I'm ${it.name} !"
            Picasso.get().load(it.image).into(binding?.imgPhotoPerfil)
            binding?.description?.text = "Hi, I'm ${it.username} !"
        }

    }

    companion object{
        fun newInstance(args: UserDetailArgs) : UserDetailFragment {
            val bundle = Bundle()
            val fragment = UserDetailFragment()
            bundle.putParcelable("args", args)
            return fragment.apply { arguments = bundle }
        }
    }
}
