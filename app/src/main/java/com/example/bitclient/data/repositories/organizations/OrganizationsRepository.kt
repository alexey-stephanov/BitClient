package com.example.bitclient.data.repositories.organizations

import com.example.bitclient.data.network.datamodels.workspacesmodel.networkmodels.WorkspacesResponse

interface OrganizationsRepository {
    suspend fun retrieveWorkspaces(page: Int): WorkspacesResponse
}