package com.example.understandingmvvm.mock

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel(){

    val userDataResponse: LiveData<UserDataResponse> get() = repository.userDataResponse

    fun fetchAllUserData(){
        repository.fetchAllUserData()
    }
    fun storeUserData(userData: UserData){
        repository.storeUserData(userData = userData)
    }
}