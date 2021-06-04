package com.example.bitclient.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitclient.data.repositories.settings.SettingsRepository
import com.example.bitclient.data.user.UserManager
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val userManager: UserManager,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    fun logout() {
        viewModelScope.launch {
            settingsRepository.logout()
        }
    }

    fun clearCache() {
        viewModelScope.launch {
            settingsRepository.clearCache()
        }
    }
}