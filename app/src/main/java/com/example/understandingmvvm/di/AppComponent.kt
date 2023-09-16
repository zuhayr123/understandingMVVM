package com.example.understandingmvvm.di

import android.app.Application
import com.example.understandingmvvm.MyApp
import com.example.understandingmvvm.di.modules.AppModule
import com.example.understandingmvvm.di.modules.DatabaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, DatabaseModule::class])
interface AppComponent : AndroidInjector<MyApp> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}