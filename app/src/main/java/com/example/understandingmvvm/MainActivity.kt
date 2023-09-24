package com.example.understandingmvvm

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.understandingmvvm.databinding.ActivityMainBinding
import com.example.understandingmvvm.di.ViewModelFactory
import com.example.understandingmvvm.mock.SignUpViewModel
import com.google.gson.Gson
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: SignUpViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = this.viewModelFactory.create(SignUpViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel.fetchAllUserData()

        viewModel.let{
            it.userDataResponse.observe(this, Observer {userDataResponse ->
                Log.e("MainActivity", "Some data received in observer ${Gson().toJson(userDataResponse)}")
            })
        }
    }
}