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
import com.picpay.desafio.android.presentation.adapter.UserListAdapter
import com.picpay.desafio.android.presentation.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.picpay.desafio.databinding.UserListFragment

class UserListFragment : Fragment(R.layout.fragment_user_list) {

    private var binding: UserListFragmentBinding? = null

    private val adapter: UserListAdapter by lazy { UserListAdapter() }
    private val viewModel: UserViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        setupRecyclerView()
        viewModel.getUsers()

    }

    private fun setupRecyclerView() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        progressBar.visibility = View.VISIBLE
    }

    private fun observeViewModel() {
        viewModel.loading.observe(this, Observer { isLoading ->
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })

        viewModel.users.observe(this, Observer { usersList ->
            progressBar.visibility = View.GONE
            adapter.users = usersList
        })

        viewModel.error.observe(this, Observer { errorMessage ->
            progressBar.visibility = View.GONE
            recyclerView.visibility = View.GONE
            Toast.makeText(this@UserListFragment, getString(errorMessage), Toast.LENGTH_SHORT).show()
        })
    }

}