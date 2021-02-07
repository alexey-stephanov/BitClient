package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.withTransaction
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.database.RepositoriesDatabase
import com.example.bitclient.data.user.UserManager
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val userManager: UserManager,
) : ViewModel() {

    fun logout() {
        viewModelScope.launch {
            userManager.logout()
        }
    }
}