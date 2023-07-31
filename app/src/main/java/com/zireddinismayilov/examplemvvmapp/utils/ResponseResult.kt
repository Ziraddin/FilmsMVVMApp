package com.zireddinismayilov.examplemvvmapp.utils

import com.zireddinismayilov.examplemvvmapp.data.response.MovieItem

sealed class ResponseResult {
    class Loading(var showLoading: Boolean) : ResponseResult()
    class Success<T>(var data: T) : ResponseResult()
    class Error(var stackTrace: String) : ResponseResult()
}
