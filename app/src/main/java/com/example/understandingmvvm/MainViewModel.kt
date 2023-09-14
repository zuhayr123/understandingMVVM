package com.example.understandingmvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val _userLiveData = MutableLiveData<User>()
    val userLiveData: LiveData<User> get() = _userLiveData

    suspend fun getUser(id: Int) {
        // Simulate a network or database call to fetch the user
        withContext(Dispatchers.IO) {
            val user = User(id, "John Doe")
            _userLiveData.postValue(user)
        }
    }
}