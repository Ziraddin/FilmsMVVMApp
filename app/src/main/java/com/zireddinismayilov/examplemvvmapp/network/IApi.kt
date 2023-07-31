package com.zireddinismayilov.examplemvvmapp.network

import com.zireddinismayilov.examplemvvmapp.data.request.User
import com.zireddinismayilov.examplemvvmapp.data.request.response
import com.zireddinismayilov.examplemvvmapp.data.response.MovieItem
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface IApi {
    @GET("dba1a143-0357-4ed9-a96c-a9a1c0d04f79")
    fun getMovies(): Call<List<MovieItem>>

    @POST("auth/login")
    fun getLogin(@Body user: User): Call<response>
}