package com.example.understandingmvvm.di

import android.app.Application
import com.example.understandingmvvm.MyApp
import com.example.understandingmvvm.di.modules.AppModule
import com.example.understandingmvvm.di.modules.DatabaseModule
import com.example.understandingmvvm.di.modules.FragmentModule
import com.example.understandingmvvm.di.modules.RepositoryModule
import com.example.understandingmvvm.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, DatabaseModule::class, RepositoryModule::class, ViewModelModule::class, FragmentModule::class])
interface AppComponent : AndroidInjector<MyApp> {

    fun viewModelFactory(): ViewModelFactory
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}