package com.example.bitclient.viewmodels

import androidx.paging.ExperimentalPagingApi
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.network.datamappers.RepositoryDataMapper
import com.example.bitclient.data.repositories.repositories.RepositoriesRepository
import javax.inject.Inject

class RepositoriesViewModelFactory @Inject constructor(
    private val repositoriesRepository: RepositoriesRepository,
    private val database: AccountDatabase,
    private val dataMapper: RepositoryDataMapper
) {
    @ExperimentalPagingApi
    fun create(): RepositoriesViewModel = RepositoriesViewModel(repositoriesRepository, database, dataMapper)
}