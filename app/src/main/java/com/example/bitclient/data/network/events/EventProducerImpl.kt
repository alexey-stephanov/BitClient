package com.example.bitclient.data.network.events

import kotlinx.coroutines.flow.*
import javax.inject.Inject

class EventProducerImpl @Inject constructor() : EventProducer {
    private val codeFlow = MutableSharedFlow<String>()
    override val codeState: SharedFlow<String> = codeFlow.asSharedFlow()

    override suspend fun pushCode(code: String) {
        codeFlow.emit(code)
    }
}