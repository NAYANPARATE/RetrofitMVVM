package com.example.designpatterns

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.designpatterns.model.GithubIssue
import com.example.designpatterns.repository.GithubRepository
import com.example.designpatterns.utils.toast
import com.example.designpatterns.view.IssueListAdaptor
import com.example.designpatterns.viewmodel.MainViewModel
import com.example.designpatterns.viewmodel.ViewModelFactory

//https://docs.github.com/en/rest/reference/issues#list-repository-issues
class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var progressBar: ProgressBar
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        initialSetup()
        setData()
    }

    private fun initializeViews() {
        progressBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.recyclerview)
    }

    private fun initialSetup() {
        viewModel = ViewModelProvider(this , ViewModelFactory(repository = GithubRepository()))[MainViewModel::class.java]
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setData() {
        viewModel.getIssues()

        showLoading()

        viewModel.getData().observe(this , { list ->
            recyclerView.adapter = IssueListAdaptor(list as ArrayList<GithubIssue>)
        })

        showError()
    }

    private fun showError() {
        viewModel.errorMessage.observe(this , {
            toast(this , it)
        })
    }

    private fun showLoading() {
        viewModel.loading.observe(this , { value ->
            if (value) {
                progressBar.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                progressBar.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        })
    }


}