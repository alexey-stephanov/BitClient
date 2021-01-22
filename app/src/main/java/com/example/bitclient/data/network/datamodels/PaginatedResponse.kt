package com.example.bitclient.data.network.datamodels

import com.google.gson.annotations.SerializedName

open class PaginatedResponse<T>(
    @SerializedName("values")
    val values: List<T>,
    @SerializedName("prev")
    val previousPage: String? = null,
    @SerializedName("next")
    val nextPage: String? = null
)