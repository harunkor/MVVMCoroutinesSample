package com.harunkor.mvvmcoroutinessample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.harunkor.mvvmcoroutinessample.domain.model.User
import com.harunkor.mvvmcoroutinessample.presentation.MainAdapter
import com.harunkor.mvvmcoroutinessample.presentation.MainViewModel
import com.harunkor.mvvmcoroutinessample.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel : MainViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    @Inject
    lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
        initUI()
        initApi()
    }

    private fun initApi() {
        mainViewModel.fetchUsers().observe(this, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let {
                        usersData -> renderList(usersData)
                        recyclerView.visibility = View.VISIBLE
                    }
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()
                }
            }
        })
    }


    private fun initUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter= MainAdapter()
        recyclerView.addItemDecoration(
            DividerItemDecoration(recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation)
        )
        recyclerView.adapter=adapter
    }


    private fun renderList(usersData: List<User>) {
        adapter.apply {
            addData(usersData)
            notifyDataSetChanged()
        }
    }
}