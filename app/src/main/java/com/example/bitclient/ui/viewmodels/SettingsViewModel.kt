package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.bitclient.data.user.UserManager
import javax.inject.Inject

class SettingsViewModel @Inject constructor(private val userManager: UserManager) : ViewModel() {

    fun logout() {
        userManager.logout()
    }
}