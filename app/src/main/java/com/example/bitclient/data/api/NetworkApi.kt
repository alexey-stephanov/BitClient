package com.example.bitclient.data.api

import com.example.bitclient.data.model.TokenModel
import retrofit2.http.*

interface NetworkApi {

    @FormUrlEncoded
    @POST("access_token")
    suspend fun getAccessToken(
            @Field("grant_type") grantType: String,
            @Field("code") code: String
    ) : TokenModel

}