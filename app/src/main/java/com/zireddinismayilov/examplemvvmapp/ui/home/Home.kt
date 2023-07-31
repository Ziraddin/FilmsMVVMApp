package com.zireddinismayilov.examplemvvmapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zireddinismayilov.examplemvvmapp.data.response.MovieItem
import com.zireddinismayilov.examplemvvmapp.databinding.ActivityHomeBinding
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
    }

    private fun setUpObservers() {
        viewModel.result.observe(this@Home, Observer {
            when (it) {
                is ResponseResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is ResponseResult.Success<*> -> {
                    binding.progressBar.visibility = View.GONE
                    binding.HomeTV.text = (it.data as List<MovieItem>)[0].Title
                }

                is ResponseResult.Error -> {

                }
            }
        })
    }
}