package com.example.bitclient.viewmodels.factories

import androidx.paging.ExperimentalPagingApi
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.network.datamappers.WorkspaceDataMapper
import com.example.bitclient.data.repositories.organizations.OrganizationsRepository
import com.example.bitclient.viewmodels.OrganizationsViewModel
import javax.inject.Inject

class OrganizationsViewModelFactory @Inject constructor(
    private val organizationsRepository: OrganizationsRepository,
    private val database: AccountDatabase,
    private val dataMapper: WorkspaceDataMapper
) {
    @ExperimentalPagingApi
    fun create(accountId: String): OrganizationsViewModel =
        OrganizationsViewModel(
            organizationsRepository,
            database,
            dataMapper,
            accountId
        )
}