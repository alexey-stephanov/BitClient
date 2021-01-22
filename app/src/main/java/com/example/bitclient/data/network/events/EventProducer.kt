package com.example.bitclient.data.network.events

import kotlinx.coroutines.flow.Flow

interface EventProducer {
    val codeState: Flow<String>
    suspend fun pushCode(code: String)
}