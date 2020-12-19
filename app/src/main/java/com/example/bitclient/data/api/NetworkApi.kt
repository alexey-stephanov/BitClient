package com.example.bitclient.data.api

import com.example.bitclient.data.models.RepositoriesModel
import com.example.bitclient.data.models.TokenModel
import com.example.bitclient.data.models.UserModel
import retrofit2.http.*

interface NetworkApi {

    @FormUrlEncoded
    @POST("access_token")
    suspend fun getAccessToken(
            @Field("grant_type") grantType: String,
            @Field("code") code: String
    ) : TokenModel

    @GET("repositories")
    suspend fun getRepositories() : List<RepositoriesModel>

    @GET("user")
    suspend fun getUserInfo(): UserModel
}