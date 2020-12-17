package com.example.bitclient.data.model

import com.google.gson.annotations.SerializedName

class TokenModel(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String
)