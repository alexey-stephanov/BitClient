package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitclient.data.models.UserModel
import com.example.bitclient.data.repositories.NetworkDataRepository
import com.example.bitclient.data.user.UserManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepositoriesViewModel @Inject constructor(private val networkDataRepository: NetworkDataRepository, private val userManager: UserManager) : ViewModel() {
    fun getUserInfo(): String = userManager.getUserInfo()
}