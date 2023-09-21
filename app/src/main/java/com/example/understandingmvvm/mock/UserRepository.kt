package com.example.understandingmvvm.mock

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository constructor(val webService: WebService, val userDAO: UserDAO) {
    private val _userDataResponse = MutableLiveData<UserDataResponse>()
    val userDataResponse: LiveData<UserDataResponse> get() = _userDataResponse

    fun storeUserData(userData: UserData){
        userDAO.insertUsers(userData = userData)
    }

    fun fetchAllUserData() {
        webService.fetchAllUsers().enqueue(object : Callback<UserDataResponse> {
            override fun onResponse(call: Call<UserDataResponse>, response: Response<UserDataResponse>) {
                if (response.isSuccessful) {
                    _userDataResponse.postValue(response.body())
                    Log.e("API_RESPONSE", "Success: ${Gson().toJson(response.body())}")
                } else {
                    val errorString = response.errorBody()?.string()
                    Log.e("API_RESPONSE", "Unsuccessful: $errorString")
                }
            }

            override fun onFailure(call: Call<UserDataResponse>, t: Throwable) {
                // Handle failure
                Log.e("API_RESPONSE", "Failure: ${t.message}")
            }
        })
    }
}