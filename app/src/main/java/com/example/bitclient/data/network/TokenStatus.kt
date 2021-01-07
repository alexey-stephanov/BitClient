package com.example.bitclient.data.network

sealed class TokenStatus {
    object Ready : TokenStatus()
    object Loading : TokenStatus()
    data class Error(val errorMessage: String) : TokenStatus()
}