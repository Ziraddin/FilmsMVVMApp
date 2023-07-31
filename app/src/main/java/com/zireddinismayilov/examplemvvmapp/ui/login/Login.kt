package com.zireddinismayilov.examplemvvmapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zireddinismayilov.examplemvvmapp.data.request.User
import com.zireddinismayilov.examplemvvmapp.databinding.ActivityLoginBinding
import com.zireddinismayilov.examplemvvmapp.ui.home.Home
import com.zireddinismayilov.examplemvvmapp.utils.ResponseResult

class Login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.toString()
            val password = binding.passwordTextInputLayout.editText.toString()
            val user = User(username, password)
            viewModel.login(user)
            setUpObservers()
        }
    }

    private fun setUpObservers() {
        viewModel.result.observe(this@Login, Observer {
            when (it) {
                is ResponseResult.Loading -> {

                }

                is ResponseResult.Success<*> -> {
                    startActivity(Intent(this@Login, Home::class.java))
                }

                is ResponseResult.Error -> {

                }
            }
        })
    }
}