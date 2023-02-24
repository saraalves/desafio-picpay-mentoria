package com.picpay.desafio.android.presentation.activity

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.presentation.adapter.UserListAdapter
import com.picpay.desafio.android.presentation.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val recyclerView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }
    private val progressBar: ProgressBar by lazy { findViewById<ProgressBar>(R.id.user_list_progress_bar) }
    private val adapter: UserListAdapter by lazy { UserListAdapter() }
    private val viewModel: UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            Toast.makeText(this@MainActivity, getString(errorMessage), Toast.LENGTH_SHORT).show()
        })
    }
}
