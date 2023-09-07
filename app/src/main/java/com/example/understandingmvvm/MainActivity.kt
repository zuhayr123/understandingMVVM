package com.example.understandingmvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var builderClassBuilder = BuilderClassBuilder().setAge(10).setName("Test").build()

//        var generated = MyGeneratedClassTenth()
    }
}