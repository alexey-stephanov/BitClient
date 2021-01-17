package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitclient.data.network.events.EventProducer
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val eventProducer: EventProducer) : ViewModel() {

    fun handleAuthorizationCode(code: String) {
        viewModelScope.launch {
            eventProducer.pushCode(code)
        }
    }
}