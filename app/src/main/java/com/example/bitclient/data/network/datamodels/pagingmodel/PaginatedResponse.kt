package com.example.bitclient.data.network.datamodels.pagingmodel

import com.google.gson.annotations.SerializedName

abstract class PaginatedResponse<DataModel>(
    @SerializedName("values")
    val values: List<DataModel>,
    @SerializedName("next")
    val nextPage: String? = null
)