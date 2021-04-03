package com.example.bitclient.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitclient.data.user.UserManager
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val userManager: UserManager,
) : ViewModel() {

    fun logout(isClearDataNeeded: Boolean) {
        viewModelScope.launch {
            userManager.logout(isClearDataNeeded)
        }
    }
}