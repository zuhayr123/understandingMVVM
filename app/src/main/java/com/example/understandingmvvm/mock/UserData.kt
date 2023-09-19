package com.example.understandingmvvm.mock

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "UserData")
class UserData {
    @PrimaryKey(autoGenerate = false)
    @SerializedName("_id")
    var uid: String = ""

    @SerializedName("first_name")
    var firstName: String? = null

    @SerializedName("last_name")
    var lastName: String? = null

    @SerializedName("phone_number")
    var phoneNumber: String? = null

    @SerializedName("email_id")
    var emailId: String? = null

    @SerializedName("password")
    var password: String? = null
}