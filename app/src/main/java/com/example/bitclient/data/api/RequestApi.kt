package com.example.bitclient.data.api

import com.example.bitclient.data.models.RepositoriesModel
import com.example.bitclient.data.models.usermodel.UserModel
import retrofit2.http.GET

interface RequestApi {

    @GET("2.0/repositories")
    suspend fun getRepositories(): List<RepositoriesModel>

    @GET("2.0/user")
    suspend fun getUserInfo(): UserModel
}