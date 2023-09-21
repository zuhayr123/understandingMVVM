package com.example.understandingmvvm.mock

import android.util.Log
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository constructor(val webService: WebService, val userDAO: UserDAO) {

    fun storeUserData(userData: UserData){
        userDAO.insertUsers(userData = userData)
    }

    fun fetchAllUserData() {
        webService.fetchAllUsers().enqueue(object : Callback<UserDataResponse>{
            override fun onResponse(call: Call<UserDataResponse>, response: Response<UserDataResponse>) {
                if (response.isSuccessful) {
                    // Log the successful response
                    val responseObj = response.body()
                    Log.e("API_RESPONSE", "Success: ${Gson().toJson(responseObj)}")
                } else {
                    // Log the unsuccessful response
                    val errorString = response.errorBody()?.string()
                    Log.e("API_RESPONSE", "Unsuccessful: $errorString")
                }
            }

            override fun onFailure(call: Call<UserDataResponse>, t: Throwable) {
                // Log the failure message
                Log.e("API_RESPONSE", "Failure: ${t.message}")
            }

        })
    }
}