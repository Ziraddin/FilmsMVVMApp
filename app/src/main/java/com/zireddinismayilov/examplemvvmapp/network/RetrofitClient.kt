package com.zireddinismayilov.examplemvvmapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    fun getMovies(): IApi {
        val retrofit = Retrofit.Builder().baseUrl("https://run.mocky.io/v3/").addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit.create(IApi::class.java)
    }

    fun getLogin(): IApi {
        val retrofit = Retrofit.Builder().baseUrl("https://dummyjson.com/").addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit.create(IApi::class.java)
    }
}