package com.example.bitclient.data.network.events

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class EventProducerImpl @Inject constructor() : EventProducer {
    private val codeFlow = MutableSharedFlow<String>()
    override val codeState: Flow<String> = codeFlow.asSharedFlow()

    override suspend fun pushCode(code: String) {
        codeFlow.emit(code)
    }
}