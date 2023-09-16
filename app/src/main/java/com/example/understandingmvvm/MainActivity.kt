package com.example.understandingmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.understandingmvvm.di.DaggerAppComponent
import com.example.understandingmvvm.mock.UserDAO
import com.example.understandingmvvm.mock.UserData
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var userDAO: UserDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val insertButton = findViewById<Button>(R.id.insert)

        insertButton.setOnClickListener {
            val data = UserData()
            data.email = "testemail@test.com"
            data.name = "test name"
            data.password = "test password"
            data.uid = "12312"

            userDAO.insertUsers(userData = data)
        }
    }
}