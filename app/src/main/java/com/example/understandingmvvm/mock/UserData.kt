package com.example.understandingmvvm.mock

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserData")
class UserData {
    @PrimaryKey(autoGenerate = false)

    var uid: String = ""

    var name: String? = null

    var email: String? = null

    var password: String? = null
}