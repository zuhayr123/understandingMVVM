package com.example.understandingmvvm.mock

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface UserDAO{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(userData: UserData)
}