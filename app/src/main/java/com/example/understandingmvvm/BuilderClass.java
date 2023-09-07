package com.example.understandingmvvm;

import android.util.Log;

import com.example.annotate.Builder;

@Builder
public class BuilderClass {

    private final String name;
    private final int age;

    BuilderClass(int age, String name){
        this.age = age;
        this.name = name;
        loggerMethod();
    }

    public void loggerMethod(){
        Log.e("Logger", "Updated object with name as " + name + " and age as " + age);
    }

}
