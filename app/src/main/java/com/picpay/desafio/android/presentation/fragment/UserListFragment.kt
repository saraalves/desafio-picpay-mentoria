package com.picpay.desafio.android.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.FragmentUserListBinding
import com.picpay.desafio.android.domain.model.response.User
import com.picpay.desafio.android.presentation.adapter.UserListAdapter
import com.picpay.desafio.android.presentation.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserListFragment : Fragment(R.layout.fragment_user_list) {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding

    private lateinit var adapter: UserListAdapter
    private var user = mutableListOf<User>()
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

        adapter = UserListAdapter { user ->
            user.let {
                activity?.supportFragmentManager?.beginTransaction()?.replace(
                    R.id.mainActivity, UserDetailFragment
                        .newInstance(UserDetailArgs(user.name.orEmpty(), user.username.orEmpty(), user.img.orEmpty()))
                )
                    ?.addToBackStack(UserDetailFragment::class.java.name)
                    ?.commit()
            }

        }
        adapter.apply {
            binding?.recyclerView?.adapter = adapter
            binding?.recyclerView?.layoutManager = LinearLayoutManager(context)
            binding?.userListProgressBar?.isVisible = true
        }
    }

    private fun observeViewModel() {
        viewModel.loading.observe(this) { isLoading ->
            if (isLoading) {
                binding?.userListProgressBar?.visibility = View.VISIBLE
            } else {
                binding?.userListProgressBar?.visibility = View.GONE
            }
        }

        viewModel.users.observe(this) { usersList ->
            binding?.userListProgressBar?.isVisible = false
            adapter.submitList(usersList)
        }

        viewModel.error.observe(this) {
            binding?.let {
                it.userListProgressBar.isVisible = false
                it.recyclerView.isVisible = false
                it.errorLayout.root.isVisible = true
            }
        }
    }

}