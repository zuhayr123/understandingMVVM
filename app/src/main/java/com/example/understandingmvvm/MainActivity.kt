package com.example.understandingmvvm

import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.understandingmvvm.mock.UserDAO
import com.example.understandingmvvm.mock.UserData
import com.example.understandingmvvm.mock.WebService
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var userDAO: UserDAO

    @Inject
    lateinit var webService: WebService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val insertButton = findViewById<Button>(R.id.insert)

        insertButton.setOnClickListener {
            webService.fetchAllUsers().enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        // Log the successful response
                        val responseString = response.body()?.string()
                        Log.e("API_RESPONSE", "Success: $responseString")
                    } else {
                        // Log the unsuccessful response
                        val errorString = response.errorBody()?.string()
                        Log.e("API_RESPONSE", "Unsuccessful: $errorString")
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    // Log the failure message
                    Log.e("API_RESPONSE", "Failure: ${t.message}")
                }
            })
        }
    }

    fun storeDataExample(){
        val data = UserData()
        data.email = "testemail@test.com"
        data.name = "test name"
        data.password = "test password"
        data.uid = "12312"

        CoroutineScope(Dispatchers.IO).launch {
            userDAO.insertUsers(userData = data)
        }
    }
}