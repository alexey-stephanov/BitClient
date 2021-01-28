package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bitclient.data.network.requests.UserDataRepository

class BranchesViewModelFactory(
    private val userDataRepository: UserDataRepository,
    private val workspaceId: String,
    private val repositoryId: String
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BranchesViewModel::class.java)) {
            return BranchesViewModel(userDataRepository, workspaceId, repositoryId) as T
        } else {
            throw IllegalArgumentException("Required BranchesViewModel class.")
        }
    }
}