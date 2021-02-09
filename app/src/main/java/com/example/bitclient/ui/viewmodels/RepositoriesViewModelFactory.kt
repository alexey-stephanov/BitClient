package com.example.bitclient.ui.viewmodels

import androidx.paging.ExperimentalPagingApi
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.network.datamodels.repositoriesmodel.RepositoryDataMapper
import com.example.bitclient.data.repositories.accountrepositories.RepositoriesRepository
import javax.inject.Inject

class RepositoriesViewModelFactory @Inject constructor(
    private val repository: RepositoriesRepository,
    private val database: AccountDatabase,
    private val dataMapper: RepositoryDataMapper
) {
    @ExperimentalPagingApi
    fun create(): RepositoriesViewModel = RepositoriesViewModel(repository, database, dataMapper)
}