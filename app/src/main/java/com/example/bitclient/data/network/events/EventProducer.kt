package com.example.bitclient.data.network.events

import kotlinx.coroutines.flow.SharedFlow

interface EventProducer {
    val codeState: SharedFlow<String>
    suspend fun pushCode(code: String)
}