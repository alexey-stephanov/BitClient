package com.example.bitclient.data.network

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow

interface EventProducer {
    val codeFlow: Flow<String>
    fun pushCode(code: String)
}