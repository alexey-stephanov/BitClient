package com.example.bitclient.data.network

sealed class ResponseStatus {
    object Success: ResponseStatus()
    object Error: ResponseStatus()
}