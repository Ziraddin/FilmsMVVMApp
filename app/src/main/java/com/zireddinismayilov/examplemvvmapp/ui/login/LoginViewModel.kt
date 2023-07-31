package com.zireddinismayilov.examplemvvmapp.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zireddinismayilov.examplemvvmapp.data.request.User
import com.zireddinismayilov.examplemvvmapp.data.request.response
import com.zireddinismayilov.examplemvvmapp.network.RetrofitClient
import com.zireddinismayilov.examplemvvmapp.utils.ResponseResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    var result = MutableLiveData<ResponseResult>()

    fun login(body: User) {
        result.postValue(ResponseResult.Loading(true))
        RetrofitClient.getLogin().getLogin(body).enqueue(object : Callback<response> {
            override fun onResponse(call: Call<response>, response: Response<response>) {
                result.postValue(ResponseResult.Success(response.body()))
            }

            override fun onFailure(call: Call<response>, t: Throwable) {
                result.postValue(ResponseResult.Error(t.stackTraceToString()))
            }

        })
    }
}