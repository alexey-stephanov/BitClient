package com.example.bitclient.data.network.requests

import com.example.bitclient.data.network.datamodels.branchesmodel.BranchesResponse
import com.example.bitclient.data.network.datamodels.commitsmodel.CommitsResponse
import com.example.bitclient.data.network.datamodels.repositoriesmodel.RepositoriesResponse
import com.example.bitclient.data.network.datamodels.usermodel.UserModel
import com.example.bitclient.data.network.datamodels.workspacesmodel.WorkspacesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RequestsApi {

    @GET("2.0/workspaces")
    suspend fun getWorkspaces(): WorkspacesResponse

    @GET("2.0/repositories/{workspace}")
    suspend fun getRepositories(
        @Path("workspace") workspaceId: String,
        @Query("page") page: Int
    ): RepositoriesResponse

    @GET("2.0/repositories/{workspace}/{repo_slug}/refs/branches")
    suspend fun getBranches(
        @Path("workspace") workspaceId: String,
        @Path("repo_slug") repositoryId: String,
        @Query("page") page: Int
    ): BranchesResponse

    @GET("2.0/repositories/{workspace}/{repo_slug}/commits/{branch_name}")
    suspend fun getCommits(
        @Path("workspace") workspaceId: String,
        @Path("repo_slug") repositoryId: String,
        @Path("branch_name") branchName: String,
        @Query("page") page: Int
    ): CommitsResponse

    @GET("2.0/user")
    suspend fun getUserInfo(): UserModel
}