package com.example.bitclient.data.network.requests

import com.example.bitclient.data.network.networkmodels.repositoriesmodel.RepositoriesResponse
import com.example.bitclient.data.network.networkmodels.usermodel.UserModel
import com.example.bitclient.data.network.networkmodels.workspacesmodel.WorkspacesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface RequestsApi {

    @GET("2.0/repositories/{workspace}")
    suspend fun getRepositories(
        @Path("workspace") workspaceId: String,
        @Query("page") page: Int
    ): RepositoriesResponse

    @GET
    suspend fun getMoreRepositories(
        @Url url: String
    )

    @GET("2.0/workspaces")
    suspend fun getWorkspaces(): WorkspacesResponse

    @GET("2.0/user")
    suspend fun getUserInfo(): UserModel
}