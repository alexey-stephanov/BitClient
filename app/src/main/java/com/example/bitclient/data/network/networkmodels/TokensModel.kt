package com.example.bitclient.data.network.networkmodels

import com.google.gson.annotations.SerializedName

data class TokensModel(
        @SerializedName("access_token")
        val accessToken: String,
        @SerializedName("refresh_token")
        val refreshToken: String
)