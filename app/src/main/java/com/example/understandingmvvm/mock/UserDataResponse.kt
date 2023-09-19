package com.example.understandingmvvm.mock

import com.google.gson.annotations.SerializedName

class UserDataResponse {
    @SerializedName("msg")
    var msg: String? = null

    @SerializedName("user")
    var data: ArrayList<UserData>? = null

    @SerializedName("status")
    var status: String? = null
}