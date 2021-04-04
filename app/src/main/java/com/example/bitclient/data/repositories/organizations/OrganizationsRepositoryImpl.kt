package com.example.bitclient.data.repositories.organizations

import com.example.bitclient.data.network.api.RequestsApi
import com.example.bitclient.data.network.datamodels.workspacesmodel.networkmodels.WorkspacesResponse
import com.example.bitclient.di.RequestQualifier
import javax.inject.Inject

class OrganizationsRepositoryImpl @Inject constructor(@RequestQualifier private val service: RequestsApi) : OrganizationsRepository {
    override suspend fun retrieveWorkspaces(page: Int): WorkspacesResponse =
        service.getWorkspaces(page)
}