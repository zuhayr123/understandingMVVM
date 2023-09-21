package com.example.understandingmvvm

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.Observer
import com.example.understandingmvvm.mock.UserDAO
import com.example.understandingmvvm.mock.UserData
import com.example.understandingmvvm.mock.UserDataResponse
import com.example.understandingmvvm.mock.UserRepository
import com.example.understandingmvvm.mock.WebService
import com.google.gson.Gson
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
    lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val insertButton = findViewById<Button>(R.id.insert)

        insertButton.setOnClickListener {
            fetchUserDataList()
        }

        userRepository.userDataResponse.observe(this, Observer {
            Log.e("Observed", "Some data received in observer ${Gson().toJson(it)}")
        })
    }

    fun storeDataExample(){
        val data = UserData()
        data.emailId = "testemail@test.com"
        data.firstName = "test name"
        data.password = "test password"
        data.uid = "12312"

        CoroutineScope(Dispatchers.IO).launch {
            userRepository.storeUserData(userData = data)
        }
    }

    fun fetchUserDataList(){
        userRepository.fetchAllUserData()
    }
}