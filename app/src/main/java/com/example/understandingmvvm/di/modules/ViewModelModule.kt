package com.example.understandingmvvm.di.modules

import androidx.lifecycle.ViewModel
import com.example.understandingmvvm.di.ViewModelKey
import com.example.understandingmvvm.mock.SignUpViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    abstract fun bindUserViewModel(userViewModel: SignUpViewModel): ViewModel
}