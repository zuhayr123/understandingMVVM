package com.example.understandingmvvm.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.understandingmvvm.mock.UserDAO
import com.example.understandingmvvm.mock.UserData

@Database(entities = [UserData::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDAO(): UserDAO
}