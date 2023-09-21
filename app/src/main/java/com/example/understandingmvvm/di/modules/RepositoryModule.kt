package com.example.understandingmvvm.di.modules

import com.example.understandingmvvm.mock.UserDAO
import com.example.understandingmvvm.mock.UserRepository
import com.example.understandingmvvm.mock.WebService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideUserRepository(webservice: WebService, dao: UserDAO): UserRepository {
        return UserRepository(webService = webservice, userDAO = dao)
    }
}