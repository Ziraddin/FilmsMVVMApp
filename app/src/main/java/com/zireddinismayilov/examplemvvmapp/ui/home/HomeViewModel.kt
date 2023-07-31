package com.zireddinismayilov.examplemvvmapp.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zireddinismayilov.examplemvvmapp.data.response.MovieItem
import com.zireddinismayilov.examplemvvmapp.network.RetrofitClient
import com.zireddinismayilov.examplemvvmapp.utils.ResponseResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    var result: MutableLiveData<ResponseResult> = MutableLiveData()

    fun getMovies() {
        result.postValue(ResponseResult.Loading(true))

        CoroutineScope(Dispatchers.IO).launch {

            RetrofitClient.getMovies().getMovies().enqueue(object : Callback<List<MovieItem>> {

                override fun onResponse(call: Call<List<MovieItem>>, response: Response<List<MovieItem>>) {
                    val data = response.body()
                    Log.d("Size", data?.size.toString())
                    result.postValue(ResponseResult.Success(data))
                }

                override fun onFailure(call: Call<List<MovieItem>>, t: Throwable) {
                    result.postValue(ResponseResult.Error(t.stackTraceToString()))
                }

            })
        }
    }
}