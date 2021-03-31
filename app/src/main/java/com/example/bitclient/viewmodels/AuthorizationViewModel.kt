package com.example.bitclient.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitclient.data.network.events.EventProducer
import com.example.bitclient.data.repositories.authorization.AuthorizationRepository
import com.example.bitclient.data.storage.Storage
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(
    private val authorizationRepository: AuthorizationRepository,
    private val storage: Storage,
    private val eventProducer: EventProducer
) : ViewModel() {

    val resultLiveData: MutableLiveData<Boolean> = MutableLiveData()

    init {
        viewModelScope.launch {
            eventProducer.codeState.collect { code ->
                try {
                    val tokens = authorizationRepository.retrieveTokens(code)
                    storage.saveTokens(tokens)
                    resultLiveData.postValue(true)
                } catch (e: CancellationException) {
                    resultLiveData.postValue(false)
                    Timber.e("Tokens response error: $e")
                }
            }
        }
    }
}