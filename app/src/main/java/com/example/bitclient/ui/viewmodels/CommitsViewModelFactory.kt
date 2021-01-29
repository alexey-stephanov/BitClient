package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bitclient.data.repositories.userrepositories.UserRepositoriesRepository

class CommitsViewModelFactory(
    private val userRepositoriesRepository: UserRepositoriesRepository,
    private val workspaceId: String,
    private val repositoryId: String,
    private val branchName: String
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommitsViewModel::class.java)) {
            return CommitsViewModel(
                userRepositoriesRepository,
                workspaceId,
                repositoryId,
                branchName
            ) as T
        } else {
            throw IllegalArgumentException("Required CommitsViewModel class.")
        }
    }
}