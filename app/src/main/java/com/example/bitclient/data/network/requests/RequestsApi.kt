package com.example.bitclient.data.network.requests

import com.example.bitclient.data.network.networkmodels.TokensModel
import com.example.bitclient.data.network.networkmodels.repositoriesmodel.RepositoriesResponse
import com.example.bitclient.data.network.networkmodels.usermodel.UserModel
import com.example.bitclient.data.network.networkmodels.workspacesmodel.WorkspacesResponse
import retrofit2.http.*

interface RequestsApi {

    @FormUrlEncoded
    @POST("site/oauth2/access_token")
    suspend fun refreshAccessToken(
        @Field("grant_type") grantType: String,
        @Field("refresh_token") refreshToken: String
    ): TokensModel

    @GET("2.0/repositories/{workspace}")
    suspend fun getRepositories(
        @Path("workspace") workspaceId: String,
        @Query("page") page: Int
    ): RepositoriesResponse

    @GET("2.0/workspaces")
    suspend fun getWorkspaces(): WorkspacesResponse

    @GET("2.0/user")
    suspend fun getUserInfo(): UserModel
}