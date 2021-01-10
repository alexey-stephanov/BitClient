package com.example.bitclient.data.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EventProducerImpl @Inject constructor(): EventProducer {

    override val codeFlow: Flow<String> = MutableStateFlow("")

    override fun pushCode(code: String) {

    }

//    fun getObservableCodeEvent(code: String): Flow<String> {
//
//    }
}