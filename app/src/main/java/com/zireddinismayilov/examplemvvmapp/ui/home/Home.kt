package com.zireddinismayilov.examplemvvmapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zireddinismayilov.examplemvvmapp.data.response.MovieItem
import com.zireddinismayilov.examplemvvmapp.databinding.ActivityHomeBinding
import com.zireddinismayilov.examplemvvmapp.utils.Adapters.MoviesAdapter
import com.zireddinismayilov.examplemvvmapp.utils.ResponseResult

class Home : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var viewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.getMovies()
        setUpObservers()
        setUpRefreshing()
    }

    private fun setUpObservers() {
        viewModel.result.observe(this@Home, Observer {
            when (it) {
                is ResponseResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is ResponseResult.Success<*> -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    setUpRecyclerView(it.data as MutableList<MovieItem>)
                }

                is ResponseResult.Error -> {

                }
            }
        })
    }

    private fun setUpRecyclerView(movielist: MutableList<MovieItem>) {
        binding.MoviesRecyclerView.adapter = MoviesAdapter(movielist, this)
        binding.MoviesRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun setUpRefreshing() {
        binding.swipeRefreshLayout.apply {
            setOnRefreshListener {
                setUpObservers()
                isRefreshing = false
            }
        }
    }


}