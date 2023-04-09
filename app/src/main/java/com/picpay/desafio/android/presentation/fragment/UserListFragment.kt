package com.picpay.desafio.android.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.FragmentUserListBinding
import com.picpay.desafio.android.presentation.adapter.UserListAdapter
import com.picpay.desafio.android.presentation.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserListFragment : Fragment(R.layout.fragment_user_list) {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding
    
    private val adapter: UserListAdapter by lazy { UserListAdapter() }
    private val viewModel: UserViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding?.fragmentUserList

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        setupRecyclerView()
        viewModel.getUsers()

    }

    private fun setupRecyclerView() {
        binding?.recyclerView?.adapter = adapter
        binding?.recyclerView?.layoutManager = LinearLayoutManager(context)
        binding?.userListProgressBar?.visibility = View.VISIBLE
    }

    private fun observeViewModel() {
        viewModel.loading.observe(this, Observer { isLoading ->
            if (isLoading) {
                binding?.userListProgressBar?.visibility = View.VISIBLE
            } else {
                binding?.userListProgressBar?.visibility = View.GONE
            }
        })

        viewModel.users.observe(this, Observer { usersList ->
            binding?.userListProgressBar?.visibility = View.GONE
            adapter.users = usersList
        })

        viewModel.error.observe(this, Observer { errorMessage ->
            binding?.userListProgressBar?.visibility = View.GONE
            binding?.recyclerView?.visibility = View.GONE
            Toast.makeText(context, getString(errorMessage), Toast.LENGTH_SHORT).show()
        })
    }

}