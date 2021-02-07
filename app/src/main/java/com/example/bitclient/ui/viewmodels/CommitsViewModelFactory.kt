package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import com.example.bitclient.data.database.CommitsDao
import com.example.bitclient.data.repositories.userrepositories.RepositoriesRepository

class CommitsViewModelFactory(
    private val repositoriesRepository: RepositoriesRepository,
    private val commitsDao: CommitsDao,
    private val workspaceId: String,
    private val repositoryId: String,
    private val branchName: String
) : ViewModelProvider.Factory {

    @ExperimentalPagingApi
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommitsViewModel::class.java)) {
            return CommitsViewModel(
                repositoriesRepository,
                commitsDao,
                workspaceId,
                repositoryId,
                branchName
            ) as T
        } else {
            throw IllegalArgumentException("Required CommitsViewModel class.")
        }
    }
}