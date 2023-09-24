package com.example.understandingmvvm.di.modules

import com.example.understandingmvvm.SignupFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeMyFragment(): SignupFragment
}