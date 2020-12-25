package com.example.bitclient.data.api

import com.example.bitclient.data.models.TokenModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST("site/oauth2/access_token")
    suspend fun getAccessToken(
            @Field("grant_type") grantType: String,
            @Field("code") code: String
    ): TokenModel

    @FormUrlEncoded
    @POST("site/oauth2/access_token")
    suspend fun refreshAccessToken(
            @Field("grant_type") grantType: String,
            @Field("refresh_token") refreshToken: String
    ): TokenModel

    @FormUrlEncoded
    @POST("site/oauth2/access_token")
    fun refreshAccessToken2(
            @Field("grant_type") grantType: String,
            @Field("refresh_token") refreshToken: String
    ): TokenModel
}