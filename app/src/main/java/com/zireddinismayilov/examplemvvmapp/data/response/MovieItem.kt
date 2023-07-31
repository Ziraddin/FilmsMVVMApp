package com.zireddinismayilov.examplemvvmapp.data.response

data class MovieItem(
    var Title: String,
    var Runtime: String,
    var Year: String,
    var Rated: String,
    var Poster: String,
    var Images: MutableList<String>
)

