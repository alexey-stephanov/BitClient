package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.bitclient.data.user.UserManager
import com.example.bitclient.data.user.UserManagerDelegate
import javax.inject.Inject

class AccountViewModel @Inject constructor(private val userManager: UserManager) : ViewModel(), UserManagerDelegate by userManager