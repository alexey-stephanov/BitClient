package com.example.bitclient.data.user

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

interface UserWorkspacesLiveDataDelegate {
    val workspaceIdFlow: Flow<String>
}