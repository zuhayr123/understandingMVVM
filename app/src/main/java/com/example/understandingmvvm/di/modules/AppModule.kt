package com.example.understandingmvvm.di.modules

import com.example.understandingmvvm.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}