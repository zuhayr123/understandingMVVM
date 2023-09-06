package com.example.understandingmvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.annotate.GenerateClass

@GenerateClass(name = "MyGeneratedClass")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val generatedClass = MyGeneratedClass()
//        generatedClass.print()
    }
}