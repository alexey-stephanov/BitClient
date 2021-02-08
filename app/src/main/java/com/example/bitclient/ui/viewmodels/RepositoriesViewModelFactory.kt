package com.example.bitclient.ui.viewmodels

import androidx.paging.ExperimentalPagingApi
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.repositories.accountrepositories.RepositoriesRepository
import javax.inject.Inject

class RepositoriesViewModelFactory @Inject constructor(
    private val repository: RepositoriesRepository,
    private val database: AccountDatabase
) {
    @ExperimentalPagingApi
    fun create(): RepositoriesViewModel = RepositoriesViewModel(repository, database)
}