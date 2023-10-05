package com.example.understandingmvvm

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.understandingmvvm.databinding.ActivityAuthenticationBinding
import dagger.android.support.DaggerAppCompatActivity

class AuthenticationActivity: DaggerAppCompatActivity() {

    lateinit var binding: ActivityAuthenticationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_authentication)
    }
}