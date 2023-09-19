package com.example.understandingmvvm.mock

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface WebService {
    @GET("user")
    fun fetchAllUsers(): Call<ResponseBody>
}