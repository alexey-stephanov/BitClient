package com.example.bitclient.data.user

import kotlinx.coroutines.flow.Flow

interface UserWorkspacesLiveDataDelegate {
    val workspaceIdFlow: Flow<String>
}