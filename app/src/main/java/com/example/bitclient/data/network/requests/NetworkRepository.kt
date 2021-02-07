package com.example.bitclient.data.network.requests

import com.example.bitclient.data.network.datamodels.TokensModel

interface NetworkRepository {
    suspend fun refreshAccessToken(): TokensModel
}