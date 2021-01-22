package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitclient.data.network.ResponseStatus
import com.example.bitclient.data.network.authorization.AuthorizationDataRepository
import com.example.bitclient.data.network.events.EventProducer
import com.example.bitclient.data.storage.Storage
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(
    private val authorizationDataRepository: AuthorizationDataRepository,
    private val eventProducer: EventProducer,
    private val storage: Storage
) : ViewModel() {

    val responseStatusLiveData: MutableLiveData<ResponseStatus> = MutableLiveData()

    init {
        viewModelScope.launch {
            eventProducer.codeState.collect { code ->
                try {
                    val tokens = authorizationDataRepository.retrieveTokens(code)
                    storage.saveTokens(tokens)
                    responseStatusLiveData.postValue(ResponseStatus.Success)
                } catch (e: CancellationException) {
                    responseStatusLiveData.postValue(ResponseStatus.Error)
                }
            }
        }
    }
}