package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import com.example.bitclient.data.database.BranchesDao
import com.example.bitclient.data.repositories.userrepositories.RepositoriesRepository

class BranchesViewModelFactory(
    private val repositoriesRepository: RepositoriesRepository,
    private val branchesDao: BranchesDao,
    private val workspaceId: String,
    private val repositoryId: String
) : ViewModelProvider.Factory {

    @ExperimentalPagingApi
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BranchesViewModel::class.java)) {
            return BranchesViewModel(repositoriesRepository, branchesDao, workspaceId, repositoryId) as T
        } else {
            throw IllegalArgumentException("Required BranchesViewModel class.")
        }
    }


}